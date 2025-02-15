package brs.model;

public class Bus {
	private int busId;
	private String busName;
	private String source;
	private String destination;
	private int totalSeats;
	private int availableSeats;
	private double ticketPrice;

	// Constructors, getters, and setters
	public Bus(int busId, String busName, String source, String destination, int totalSeats, double ticketPrice) {
		this.busId = busId;
		this.busName = busName;
		this.source = source;
		this.destination = destination;
		this.totalSeats = totalSeats;
		this.availableSeats = totalSeats;
		this.ticketPrice = ticketPrice;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", busName=" + busName + ", source=" + source + ", destination=" + destination
				+ ", totalSeats=" + totalSeats + ", availableSeats=" + availableSeats + ", ticketPrice=" + ticketPrice
				+ "]";
	}
}
