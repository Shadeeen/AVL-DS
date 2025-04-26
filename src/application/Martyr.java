package application;

public class Martyr implements Comparable<Martyr> {

	private String name;
	private String date;
	private int age;
	private String location;
	private String district;
	private String gender;

	public Martyr() {

	}

	public Martyr(String name) {
		this.name = name;
	}

	public Martyr(String name, String date, int age, String location, String district, String gender) {
		this.name = name;
		this.date = date;
		this.age = age;
		this.location = location;
		this.district = district;
		this.gender = gender;
	}
	public Martyr(String name, int age, String location, String district, String gender) {
		this.name = name;
		this.age = age;
		this.location = location;
		this.district = district;
		this.gender = gender;
	}

	public Martyr(String name, String district) {
		this.name = name;
		
		this.district = district;
		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return name + "";
	}

	@Override
	public int compareTo(Martyr o) {
		if (district.compareToIgnoreCase(o.district) == 0) {
			return (this.name.compareToIgnoreCase(o.name));
		} else
			return district.compareToIgnoreCase(o.district);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Martyr)
			return this.getName().equalsIgnoreCase(((Martyr) o).getName());
		return false;
	}

}
