package plotme

class Parse {

	public static ArrayList<String[]> getArray (String S) throws FileNotFoundException{

		String line = "";

		ArrayList <String[]> myList = new ArrayList<String[]>();

		Scanner input	=	new Scanner(new File(S));
		int row = 0;
		while ((input.hasNext())) {

			line = input.nextLine();
			String []a = line.split(",");

			myList.add(a);
			System.out.println("row: " + row);
			row++;
		}
		return myList;
	}

	public static Data getPair (ArrayList <String[]> s, int x, int y) {
		
		ArrayList<Point> k = new ArrayList<Point>();

		int a = (s.get(0)).length;
		for (int i=0; i<a; i++) {

			if (s.get(y)[i].equals(""))  {
				Point p = new Point ((s.get(x))[i],0);
				k.add(p);
				continue;
			}

			try {
				Point p = new Point ((s.get(x))[i],Double.parseDouble(s.get(y)[i]));
			}

			catch (java.lang.NumberFormatException e) {
				continue;
			}

			Point p = new Point ((s.get(x))[i],Double.parseDouble(s.get(y)[i]));
			k.add(p);
		}
		
		return new Data ("","","",k);

	}
	
}
