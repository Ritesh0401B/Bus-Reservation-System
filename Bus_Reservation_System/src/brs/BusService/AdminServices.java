package brs.BusService;

import java.util.List;
import java.util.Scanner;
import brs.Dao.BookingDao;
import brs.Dao.BusDao;
import brs.Dao.CustomerDao;
import brs.model.Booking;
import brs.model.Bus;
import brs.model.Customer;

public class AdminServices {

	// Admin Menu
	public static void showAdminMenu(Scanner scanner, BusDao busDao, CustomerDao customerDao, BookingDao bookingDao) {
		while (true) {
			System.out.println("\n--- Admin Menu ---");
			System.out.println("1. Add Bus");
			System.out.println("2. View All Buses");
			System.out.println("3. Update Available Seats");
			System.out.println("4. Delete Bus");
			System.out.println("5. Generate Bus Report");
			System.out.println("6. View All Customers");
			System.out.println("7. Get Customer by ID");
			System.out.println("8. View All Bookings");
			System.out.println("9. Get Bookings by Customer ID");
			System.out.println("10. Get Bookings by Bus ID");
			System.out.println("11. Get Booking by ID");
			System.out.println("12. Cancel a Booking");
			System.out.println("13. Delete a Customer");
			System.out.println("14. Logout");
			System.out.print("Choose an option: ");

			int adminChoice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			try {
				switch (adminChoice) {
				case 1:
					System.out.print("Enter Bus Name: ");
					String name = scanner.nextLine();
					System.out.print("Enter Source: ");
					String source = scanner.nextLine();
					System.out.print("Enter Destination: ");
					String destination = scanner.nextLine();
					System.out.print("Enter Total Seats: ");
					int totalSeats = scanner.nextInt();
					System.out.print("Enter Ticket Price: ");
					double price = scanner.nextDouble();
					scanner.nextLine(); // Consume newline

					Bus bus = new Bus(0, name, source, destination, totalSeats, price);
					busDao.addBus(bus);
					break;
				case 2:
					List<Bus> buses = busDao.getAllBuses();
					if (buses.isEmpty()) {
						System.out.println("No buses found.");
					} else {
						System.out.println("\n--- Available Buses ---");

						System.out.println(
								"+----+----------------+------------+------------+--------------+-----------------+-------------+");
						System.out.printf("| %-2s | %-14s | %-10s | %-10s | %-11s | %-15s | %-11s |\n", "ID",
								"Bus Name", "Source", "Destination", "Total Seats", "Available Seats", "Price (₹)");
						System.out.println(
								"+----+----------------+------------+------------+--------------+-----------------+-------------+");

						for (Bus b : buses) {
							System.out.printf("| %-2d | %-14s | %-10s | %-10s | %-11d | %-16d | ₹%-10.2f |\n",
									b.getBusId(), b.getBusName(), b.getSource(), b.getDestination(), b.getTotalSeats(),
									b.getAvailableSeats(), b.getTicketPrice());
						}
						System.out.println(
								"+----+----------------+------------+------------+-------------+----------------+-------------+\n");
					}
					break;
				case 3:
					System.out.print("Enter Bus ID: ");
					int busId = scanner.nextInt();
					System.out.print("Enter New Available Seats: ");
					int seats = scanner.nextInt();
					scanner.nextLine(); // Consume newline
					busDao.updateAvailableSeats(busId, seats);
					System.out.println("Available seats updated successfully.");
					break;
				case 4:
					System.out.print("Enter Bus ID to delete: ");
					busId = scanner.nextInt();
					scanner.nextLine(); // Consume newline
					busDao.deleteBus(busId);
					System.out.println("Bus deleted successfully.");
					break;
				case 5:
					busDao.generateReport();
					break;
				case 6:
					List<Customer> customers = customerDao.getAllCustomers();
					if (customers.isEmpty()) {
						System.out.println("No customers found.");
					} else {
						System.out.println("\n--- Customers List ---");
						System.out.println("+----+-----------------+----------------------+------------+");
						System.out.printf("| %-2s | %-15s | %-20s | %-10s |\n", "ID", "Name", "Email", "Phone");
						System.out.println("+----+-----------------+----------------------+------------+");
						for (Customer c : customers) {
							System.out.printf("| %-2d | %-15s | %-20s | %-10s |\n", c.getCustomerId(), c.getName(),
									c.getEmail(), c.getPhone());
						}
						System.out.println("+----+-----------------+----------------------+------------+");
					}
					break;
				case 7:
					System.out.print("Enter Customer ID: ");
					int customerId = scanner.nextInt();
					scanner.nextLine();

					Customer customer = customerDao.getCustomerById(customerId);
					if (customer == null) {
						System.out.println("No customer found with ID: " + customerId);
					} else {
						System.out.println("\n--- Customers Details ---");
						System.out.println("+----+-----------------+----------------------+------------+");
						System.out.printf("| %-2s | %-15s | %-20s | %-10s |\n", "ID", "Name", "Email", "Phone");
						System.out.println("+----+-----------------+----------------------+------------+");

						System.out.printf("| %-2d | %-15s | %-20s | %-10s |\n", customer.getCustomerId(),
								customer.getName(), customer.getEmail(), customer.getPhone());

						System.out.println("+----+-----------------+----------------------+------------+");
					}
					break;
				case 8:
					List<Booking> bookings = bookingDao.getAllBookings();
					if (bookings.isEmpty()) {
						System.out.println("No bookings found.");
					} else {

						System.out.println("\n--- Booking List ---");
						for(Booking b : bookings) {
							System.out.println();
						}
					}
					break;
				case 14:
					System.out.println("Logged out from Admin Menu.");
					return;
				default:
					System.out.println("Invalid option. Please try again.");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

}
