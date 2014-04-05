package src;

import java.util.ArrayList;
import java.util.List;


public class Intersection {
	
	private int id;
	
	private double latitude;
	private double longitude;
	
	public boolean discovered;
	public boolean processed;
	
	Intersection parent;
	
	private List<Street> streetsFrom ;
	private List<Street> streetsTo;

	public Intersection(int _id, double _latitude, double _longitude) {
		this.parent = null;
		this.discovered = false;
		this.processed = false;
		this.id = _id; 
		this.latitude = _latitude; 
		this.longitude = _longitude; 
		this.streetsFrom = new ArrayList<Street>();
		this.streetsTo = new ArrayList<Street>();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Street> getStreetsFrom() {
		return streetsFrom;
	}

	public void setStreetsFrom(List<Street> streetsFrom) {
		this.streetsFrom = streetsFrom;
	}

	public List<Street> getStreetsTo() {
		return streetsTo;
	}

	public void setStreetsTo(List<Street> streetsTo) {
		this.streetsTo = streetsTo;
	}
	
	public void addToStreetsFrom(Street street) {
		this.streetsFrom.add(street);
	}
	
	public void addToStreetsTo(Street street) {
		this.streetsTo.add(street);
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((streetsFrom == null) ? 0 : streetsFrom.hashCode());
		result = prime * result
				+ ((streetsTo == null) ? 0 : streetsTo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intersection other = (Intersection) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		if (streetsFrom == null) {
			if (other.streetsFrom != null)
				return false;
		} else if (!streetsFrom.equals(other.streetsFrom))
			return false;
		if (streetsTo == null) {
			if (other.streetsTo != null)
				return false;
		} else if (!streetsTo.equals(other.streetsTo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Intersection [id=" + id + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", streetsFrom=" + streetsFrom
				+ ", streetsTo=" + streetsTo + "]";
	}
	
}
