package src;


public class Street {
	
	private Intersection begin;
	private Intersection end;
	
	private boolean oneWay;
	
	private int cost;
	private int length;
	
	float speed;
	boolean visited;
	
	
	public Street(Intersection begin, Intersection end, boolean s, int cost, int length) {
		this.begin = begin;
		this.end = end;
		this.cost = cost;
		this.length = length;
		this.oneWay = s;
		this.visited = false;
		this.speed = (float) (length*1.0/cost);
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

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((begin == null) ? 0 : begin.hashCode());
		result = prime * result + cost;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + length;
		result = prime * result + (oneWay ? 1231 : 1237);
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
		Street other = (Street) obj;
		if (begin == null) {
			if (other.begin != null)
				return false;
		} else if (!begin.equals(other.begin))
			return false;
		if (cost != other.cost)
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (length != other.length)
			return false;
		if (oneWay != other.oneWay)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Street [begin=" + begin + ", end=" + end + ", cost=" + cost
				+ ", length=" + length + ", oneWay=" + oneWay + "]";
	}

}
