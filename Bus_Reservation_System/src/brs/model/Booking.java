package brs.model;

import java.time.LocalDateTime;

public class Booking {
	private int bookingId;
	private int busId;
	private int customerId;
	private int seatsBooked;
	private LocalDateTime bookingTime;

	// Constructors, getters, and setters
	public Booking(int bookingId, int busId, int customerId, int seatsBooked, LocalDateTime localDateTime) {
		this.bookingId = bookingId;
		this.busId = busId;
		this.customerId = customerId;
		this.seatsBooked = seatsBooked;
		this.bookingTime = LocalDateTime.now();
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getSeatsBooked() {
		return seatsBooked;
	}

	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

	public LocalDateTime getBookingTime() {
		return bookingTime;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", busId=" + busId + ", customerId=" + customerId + ", seatsBooked="
				+ seatsBooked + ", bookingTime=" + bookingTime + "]";
	}
}
