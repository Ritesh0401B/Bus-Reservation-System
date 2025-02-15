package brs.BusService;

import java.util.List;
import java.util.Scanner;

import brs.Dao.BookingDao;
import brs.Dao.BusDao;
import brs.model.Booking;
import brs.model.Bus;

public class CustomerServices {
	
	// Customer Menu
    public static void showCustomerMenu(Scanner scanner, BusDao busDao, BookingDao bookingDao, int customerId) {   
        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Available Buses");
            System.out.println("2. Book a Ticket");
            System.out.println("3. View My Bookings");
            System.out.println("4. Cancel a Booking");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int customerChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            try {
                switch (customerChoice) {
                    case 1:
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
                    case 2:
                        System.out.print("Enter Bus ID: ");
                        int busId = scanner.nextInt();
                        System.out.print("Enter Number of Seats: ");
                        int seats = scanner.nextInt();
                        scanner.nextLine();  // Consume newline

                        Booking booking = new Booking(0, busId, customerId, seats, null);
                        bookingDao.addBooking(booking);
                        
                        break;
                    case 3:
                        List<Booking> bookings = bookingDao.getBookingsByCustomerId(customerId);
                        if (bookings.isEmpty()) {
                            System.out.println("No bookings found.");
                        } else {
                            System.out.println("\n--- My Bookings ---");
                            for (Booking b : bookings) {
                                System.out.println(b);
                            }
                        }
                        break;
                    case 4:
                        System.out.print("Enter Booking ID to cancel: ");
                        int bookingId = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        bookingDao.cancelBooking(bookingId);;
                        System.out.println("Booking canceled successfully.");
                        break;
                    case 5:
                        System.out.println("Logged out from Customer Menu.");
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
