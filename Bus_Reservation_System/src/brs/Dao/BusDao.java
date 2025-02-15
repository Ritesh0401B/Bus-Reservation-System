package brs.Dao;

import java.sql.SQLException;
import java.util.List;

import brs.Exception.BusException;
import brs.model.Bus;

public interface BusDao {
	
	 // Method to add a new bus
    void addBus(Bus bus) throws BusException;

    // Method to get all bus details
    List<Bus> getAllBuses() throws BusException;

    // Method to update available seats for a specific bus
    void updateAvailableSeats(int busId, int seats) throws BusException;

    // Method to delete a specific bus
    void deleteBus(int busId) throws BusException;

    // Method to get details of a specific bus by ID
    Bus getBusById(int busId) throws BusException;


    // Method to update complete bus details
    void updateBusDetails(Bus bus) throws BusException;

    // Optional: Generate a report for bookings and seat availability
    void generateReport() throws SQLException;

    // Optional: Manage user details
    void manageUsers();

	

}
