package plotme

class Parse {

	// This method creates an array list containing all the data from the given
	// Dataset where each element is
	// A row from the dataset
	public static ArrayList<String[]> getArray(String S)
			throws FileNotFoundException {

		String line = ""; // intiliaze empty string
		ArrayList<String[]> myList = new ArrayList<String[]>(); // intialize new
																// arraylist for
																// storage
		Scanner input = new Scanner(new File(S)); // dataset to be stored = S
		while ((input.hasNext())) { // continueing running loop while there
									// eixsts a next line in the dataset
			line = input.nextLine();
			String[] a = line.split(","); // split each line as a list of
											// strings seperated by commas
			myList.add(a); // add each list to the arraylist
		}
		return myList; // return the arraylist when finished
	}

	// this method is used to create points (independent and dependent variables) by using columns
	public static Data getPairCol(ArrayList<String[]> s, int x,
			int y) {

		ArrayList<Point> k = new ArrayList<Point>();

		
		for (int i = 0; i < s.size(); i++) {  //iterate through the entire arraylist

			if (s.get(i)[y].equals("")) {			//assume value 0 if entry is empty
				Point p = new Point((s.get(i))[x], 0);
				k.add(p);
				continue;
			}
			try {
				Double.parseDouble(s.get(i)[y]);
			} catch (java.lang.NumberFormatException e) {  //check to ensure a string isn't parsed as a double
				continue;
			}
			Point p = new Point((s.get(i))[x], Double.parseDouble(s.get(i)[y]));
			k.add(p);
		}
		return new Data("","","",k);

	}

	//this method is used to create points (independent and dependent) by utilizing rows
	public static Data getPairRow(ArrayList<String[]> s, int x, int y) {

		ArrayList<Point> k = new ArrayList<Point>();
		int a = (s.get(0)).length; //get the length of each row in the array list

		for (int i = 0; i < a; i++)  {		//iterate through the row

			if (s.get(y)[i].equals("")) {

				Point p = new Point((s.get(x))[i], 0); //
				k.add(p);
				continue;
				}

			try {
				Double.parseDouble(s.get(y)[i]);
			} catch (java.lang.NumberFormatException e) {		//check to ensure the string isn't parsed as a double
				continue;
			}
			Point p = new Point((s.get(x))[i], Double.parseDouble(s.get(y)[i]));
			k.add(p);
		}
		return new Data("","","",k);

	}
	
	
	//this returns an arraylist of the required points using the column or row method depending on what the user specifies
	public static Data getPair(ArrayList<String[]> s, int x, int y, String u) {
		
		//Data a = new Data(u, u, u, s);
		if (u.equals("row")) {
		 Data a = getPairRow(s,  x,  y);
		 return a;
		}
		else {
			
			Data a = getPairCol(s,  x,  y);
			return a;
			}
		
	}
	
	
	//the following methods are all used to make it convenient for the user to get a row or column number that contains
	//the specified string after searching for it using a BST

	//this will create and return BST  given a row number for the ArrayList which can return the column number for a String
	 public static RedBlackBST <String, Integer> getBSTRow (ArrayList<String[]> a, int x) {
		 RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
		 
		 for (int i = 0; i<a.size(); i++) {
		 
		 st.put(a.get(i)[x],i); }
		 
		 return st;
		 
	 }
	 
	 //this will create and return a BST that can return the column number given the row numnber
	 
	 public static RedBlackBST <String, Integer> getBSTCol (ArrayList<String[]> a, int x) {
		 RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
		 
		 for (int i = 0; i<a.get(1).length; i++) {
		 
		 st.put(a.get(x)[i].replace("\"", ""),i); }
		 
		 return st;
		 
	 }
	 
	 //this will call the specified method depending on whether the client wants to store row or column numbers
	 
	 public static RedBlackBST <String, Integer> getBST (ArrayList<String[]> a, int x, String okay) {
		 RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
		 if (okay.equals("row")) {
			 st = getBSTRow (a,x);}
		 
		 else {
			 st= getBSTCol(a,x);}
		 
	 return st; }
	 
	
	/**************************************************************************/
	
//	public static ArrayList<String[]> getArray (String S) throws FileNotFoundException{
//
//		String line = "";
//
//		ArrayList <String[]> myList = new ArrayList<String[]>();
//
//		Scanner input	=	new Scanner(new File(S));
//		int row = 0;
//		while ((input.hasNext())) {
//
//			line = input.nextLine();
//			String []a = line.split(",");
//
//			myList.add(a);
//			row++;
//		}
//		return myList;
//	}
//
//	public static Data getPair (ArrayList <String[]> s, int x, int y) {
//		
//		ArrayList<Point> k = new ArrayList<Point>();
//
//		int a = (s.get(0)).length;
//		for (int i=0; i<a; i++) {
//
//			if (s.get(y)[i].equals(""))  {
//				Point p = new Point ((s.get(x))[i],0);
//				k.add(p);
//				continue;
//			}
//
//			try {
//				Point p = new Point ((s.get(x))[i],Double.parseDouble(s.get(y)[i]));
//			}
//
//			catch (java.lang.NumberFormatException e) {
//				continue;
//			}
//
//			Point p = new Point ((s.get(x))[i],Double.parseDouble(s.get(y)[i]));
//			k.add(p);
//		}
//		
//		return new Data ("","","",k);
//
//	}
	
}
