package application;

public class Location implements Comparable<Location> {

	private String location;

	public Location() {
	}

	public Location(String location) {
		super();
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return location + "";
	}

	@Override
	public int compareTo(Location o) {
		return location.compareToIgnoreCase(o.location);
	}
	@Override
	public boolean equals(Object o) {
		if (o instanceof Location)
			return this.getLocation().equalsIgnoreCase(((Location) o).getLocation());
		return false;
	}

}
