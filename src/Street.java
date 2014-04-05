package src;


public class Street {
	public Intersection begin;
	public Intersection end;
	int cost;
	int length;
	boolean oneWay;
	
	
	public Street(Intersection begin, Intersection end, boolean s, int cost, int length) {
		super();
		this.begin = begin;
		this.end = end;
		this.cost = cost;
		this.length = length;
		this.oneWay = s;
	}
	
	
	public Intersection getBegin() {
		return begin;
	}
	public void setBegin(Intersection begin) {
		this.begin = begin;
	}
	public Intersection getEnd() {
		return end;
	}
	public void setEnd(Intersection end) {
		this.end = end;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public boolean isOneWay() {
		return oneWay;
	}
	public void setOneWay(boolean oneWay) {
		this.oneWay = oneWay;
	}
	
	

}
