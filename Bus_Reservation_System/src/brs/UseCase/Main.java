package brs.UseCase;

import java.util.List;
import java.util.Scanner;

import brs.Dao.BookingDao;
import brs.Dao.BusDao;
import brs.Dao.CustomerDao;
import brs.DaoImpl.AdminDaoImpl;
import brs.DaoImpl.BookingDaoImpl;
import brs.DaoImpl.BusDaoImpl;
import brs.DaoImpl.CustomerDaoImpl;
import brs.BusService.AdminServices;
import brs.BusService.CustomerServices;
import brs.Dao.AdminDao;
import brs.model.Booking;
import brs.model.Bus;
import brs.model.Customer;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BusDao busDao = new BusDaoImpl();
		CustomerDao customerDao = new CustomerDaoImpl();
		BookingDao bookingDao = new BookingDaoImpl();
		AdminDao adminDao = new AdminDaoImpl();

		while (true) {
			System.out.println("\n--- Bus Reservation System ---");
			System.out.println("1. Admin Login");
			System.out.println("2. Customer Login");
			System.out.println("3. Customer Registration");
			System.out.println("4. Exit");
			System.out.print("Choose an option: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			if (choice == 1) { // ✅ Admin Login
				System.out.print("Enter Admin Username: ");
				String username = scanner.nextLine();
				System.out.print("Enter Admin Password: ");
				String password = scanner.nextLine();

				try {
					if (adminDao.validateAdmin(username, password)) {
						System.out.println("✅ Admin login successful.");
						AdminServices.showAdminMenu(scanner, busDao, customerDao,bookingDao);
					} else {
						System.out.println("❌ Invalid admin credentials. Please try again.");
					}
				} catch (Exception e) {
					System.out.println("Error during admin login: " + e.getMessage());
				}

			} else if (choice == 2) { // ✅ Customer Login
				System.out.print("Enter Customer ID: ");
				int customerId = scanner.nextInt();
				scanner.nextLine(); // Consume newline

				try {
					if (customerDao.validateCustomer(customerId)) {
						System.out.println("✅ Customer login successful.");
						CustomerServices.showCustomerMenu(scanner, busDao, bookingDao, customerId);
					} else {
						System.out.println("❌ Customer ID not found. Please register first.");
					}
				} catch (Exception e) {
					System.out.println("Error during customer login: " + e.getMessage());
				}

			} else if (choice == 3) { // ✅ Customer Registration
				System.out.print("Enter Customer Name: ");
				String name = scanner.nextLine();
				System.out.print("Enter Email: ");
				String email = scanner.nextLine();
				System.out.print("Enter Phone Number: ");
				String phone = scanner.nextLine();

				Customer customer = new Customer(0, name, email, phone);
				try {
					customerDao.registerCustomer(customer);
					System.out.println("✅ Customer registered successfully. You can now log in.");
				} catch (Exception e) {
					System.out.println("Error registering customer: " + e.getMessage());
				}

			} else if (choice == 4) { // ✅ Exit
				System.out.println("Exiting the system. Goodbye!");
				break;

			} else {
				System.out.println("❌ Invalid option. Please try again.");
			}
		}
		scanner.close();
	}
}
