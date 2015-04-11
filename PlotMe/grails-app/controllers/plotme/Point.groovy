package plotme

// This class is used to store the independent (strings) and dependent variables (doubles) as a pair of points
class Point implements Comparable{
	
	private String name;
	private double value;
	
	//initialize name and value
	public Point (String n, double v) 
	{this.name = n; this.value = v;	 }
	
	//setters added in case we ever want to change the value or name
	public void setName (String a)  { this.name = a; }
	public void setValue (Double a) { this.value = a;}
	
	//getters added to access the name or value for a pair
	public String getName () 		{ return name; }
	public double getValue () 		{ return value; }
	
	//print out all the name and value pairs for the dataset, used for testing purposes
	public String toString()
	{ return "("+name + " , " + value +")";	}

	@Override
	public int compareTo(Object o) {
		if(o.getClass() != getClass()) return 1;
		if(value > o.getValue()) return 1;
		if(value == o.getValue()) return 0;
		return -1;
	}
}
