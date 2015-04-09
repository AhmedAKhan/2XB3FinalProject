package plotme

class Data {
	private String Name;
	private String xaxis;
	private String yaxis;
	public ArrayList<?> data;
	
	public Data(ArrayList<?> data){ this("Name", "x axis", "y axis", data);}
	public Data (String N, String x, String y, ArrayList<?> d){
		
		this.Name=N;
		this.xaxis=x;
		this.yaxis=y;
		this.data = d;
	}

	
	public String getName() {
		return Name;
	}
	
	public String getXaxis () {
		return xaxis;
	}
	
	public String getYaxis () {
		return yaxis;
	}
	
	public ArrayList<?> getData () {
		return data;
	}
	
	public Data sort() {
		Point[] arrPoint = new Point[data.size()]
		ArrayList<Point> sortedData = new ArrayList<>()
		
		for (int i = 0; i < arrPoint.length; i++) {
			arrPoint[i] = data.get(i)
		}
		
//		for (int q = 0; q < arrPoint.length; q++) {
//			for (int p = 0; p < arrPoint.length; p++) {
//				if (arrPoint[q].getValue() > arrPoint[p].getValue()) swap(q,p,arrPoint);
//			}
//		}
		System.out.println("started: " + arrPoint);
		OptimalSort.sort(arrPoint, OptimalSort.DECREASING);
		System.out.println("ended: " + arrPoint);
		
		for (int i = 0; i < arrPoint.length; i++) {
			sortedData.add(arrPoint[i]);
		}
		
		
		return new Data(this.name,this.xaxis,this.yaxis,sortedData);
	}
	
//	private void swap(int a, int b, Point[] arr) {
//		Point temp = new Point(arr[a].getName(), arr[a].getValue());
//		arr[a] = new Point(arr[b].getName(), arr[b].getValue());
//		arr[b] = new Point(temp.getName(), temp.getValue());
//	}
	
	@Override public String toString(){
		return data.toString();	
	}
	
	public static void main(String[] args) {
		ArrayList<String[]> p = new ArrayList<>();
		
		for(int i = 0; i < 300000; i++) {
			String[] s = ["a","b","c","a","b","c","a","b","c","a","b","c"]
			println "${s}"
			p.add(s)
		}
	}
	
}
