package application;

import java.util.ArrayList;

public class District implements Comparable<District>{
	private String district;
	private LinkedList<Location> locationList=new LinkedList<>();

	public District() {

	}

	public District(String district) {
		super();
		this.district = district;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public LinkedList<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(LinkedList<Location> locationList) {
		this.locationList = locationList;
	}

	public void addLocation(Location location) {
		locationList.insert(location);
		
	}
	
	@Override
	public String toString() {
		return district + "";
	}

	@Override
	public int compareTo(District o) {
		return district.compareToIgnoreCase(o.district);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof District)
			return this.getDistrict().equalsIgnoreCase(((District) o).getDistrict());
		return false;
	}

}
