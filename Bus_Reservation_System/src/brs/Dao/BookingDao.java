package brs.Dao;

import java.sql.SQLException;
import java.util.List;

import brs.Exception.BookingException;
import brs.model.Booking;

public interface BookingDao {

	void addBooking(Booking booking) throws BookingException;

	Booking getBookingById(int bookingId) throws BookingException;
	
	public List<Booking> getBookingsByCustomerId(int customerId) throws BookingException;

	public List<Booking> getBookingsByBusId(int busId) throws BookingException;
	
	List<Booking> getAllBookings() throws BookingException;

	void cancelBooking(int bookingId) throws BookingException;

}
