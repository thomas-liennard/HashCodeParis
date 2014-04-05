package src;

import java.util.ArrayList;
import java.util.List;


public class Intersection {
	public double latitude;
	public double longitude;
	public int id;
	
	private List<Street> streetsFrom = new ArrayList<Street>();
	private List<Street> streetsTo = new ArrayList<Street>();

	public Intersection(int _id, double _latitude, double _longitude) {
		this.id = _id; 
		this.latitude = _latitude; 
		this.longitude = _longitude; 
	}
	
	
	
}
