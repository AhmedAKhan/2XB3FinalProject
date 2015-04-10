package plotme

class Data {
	private String Name;
	private String xaxis;
	private String yaxis;
	private ArrayList<Point> data;

    //contructors
	public Data(ArrayList<?> data){ this("Name", "x axis", "y axis", data);}
	public Data (String N, String x, String y, ArrayList<?> d){
		this.Name=N;
		this.xaxis=x;
		this.yaxis=y;
		this.data = d;
	}

    //getters and setters for the variables
	public String getName() {  return Name;  }
	public String getXaxis () {  return xaxis;  }
	public String getYaxis () {  return yaxis;  }
	public ArrayList<Point> getData () {  return data;  }

    //sorts the variables using the optimal sorting algorithm
	public Data sort() {
        Point[] arrPoint = new Point[data.size()]//creates a new array of points
		ArrayList<Point> sortedData = new ArrayList<>()//creates an empty arraylist

        //places everything in the arraylist in the array
		for (int i = 0; i < arrPoint.length; i++) arrPoint[i] = data.get(i)

        //sorts the array in decreasing order
		OptimalSort.sort(arrPoint, OptimalSort.type.DECREASING);

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
