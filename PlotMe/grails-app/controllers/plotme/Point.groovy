package plotme

class Point {
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
	
	public String toString(){
		return "("+name + " , " + value +")";	
	}
}
