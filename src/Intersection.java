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
	public String toString() {
		return "Intersection [id=" + id + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", streetsFrom=" + streetsFrom
				+ ", streetsTo=" + streetsTo + "]";
	}
	
}
