package plotme

// This class is used by the view to draw the graphs
// This is used to make the code more abstract and make it easier to add more parameters later on if we desired

class Data {
	private String Name;
	private String xaxis;
	private String yaxis;
	public ArrayList<?> data; // stores Point (independent, dependent) 
	
	// Initialize Data
	
	public Data(ArrayList<?> data){ this("Name", "x axis", "y axis", data);}
	public Data (String N, String x, String y, ArrayList<?> d){
		this.Name=N;
		this.xaxis=x;
		this.yaxis=y;
		this.data = d;
	}
	
	// Getters
	
	public String getName() {  return Name;  }
	public String getXaxis () {  return xaxis;  }
	public String getYaxis () {  return yaxis;  }
	public ArrayList<?> getData () {  return data;  }
	
	public Data sort() {
		Point[] arrPoint = new Point[data.size()]
		ArrayList<Point> sortedData = new ArrayList<>()

        //places everything in the arraylist in the array
		for (int i = 0; i < arrPoint.length; i++) arrPoint[i] = data.get(i)

        //sorts the array in decreasing order
		OptimalSort.sort(arrPoint, OptimalSort.type.DECREASING);
        OptimalSort.printArray(arrPoint);

        //places all the elements in the sorted array into an array list
		for (int i = 0; i < arrPoint.length; i++) sortedData.add(arrPoint[i]);

        //returns the data object of the new given information
		return new Data(this.name,this.xaxis,this.yaxis,sortedData);
	}
	
//	private void swap(int a, int b, Point[] arr) {
//		Point temp = new Point(arr[a].getName(), arr[a].getValue());
//		arr[a] = new Point(arr[b].getName(), arr[b].getValue());
//		arr[b] = new Point(temp.getName(), temp.getValue());
//	}
	
	@Override public String toString(){ return data.toString();  }
	
	public static void main(String[] args) {
		ArrayList<String[]> p = new ArrayList<>();
		
		for(int i = 0; i < 300000; i++) {
			String[] s = ["a","b","c","a","b","c","a","b","c","a","b","c"]
			println "${s}"
			p.add(s)
		}
	}
	
}
