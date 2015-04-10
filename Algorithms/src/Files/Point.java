package Files;

public class Point implements Comparable<Point> {
	private String name;
	private double value;
	
	public Point (String n, double v) {
		this.name = n;
		this.value = v;
		
	}
	
	public void setName (String a) {
		this.name = a;
	}
	
	public String getName () {
		return name;
	}
	
	public void setValue (Double a) {
		this.value = a;
	}
	
	public double getValue () {
		return value;
	}
	
    @Override public String toString(){
        return "(n: " + name + ", v: " + value+")";
    }

    @Override
    public int compareTo(Point o) {
        if(o.getClass() != getClass()) return 1;
        if(value > o.getValue()) return 1;
        if(value == o.getValue()) return 0;
        return -1;
    }
}
