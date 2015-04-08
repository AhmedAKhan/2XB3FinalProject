package Files;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class parse {
    public static ArrayList<String[]> getArray(String S) throws FileNotFoundException {


        String line = "";
        ArrayList<String[]> myList = new ArrayList<String[]>();
        Scanner input = new Scanner(new File(S));
        while ((input.hasNext())) {
            line = input.nextLine();
            String[] a = line.split(",");
            myList.add(a);
        }
        return myList;
    }

    public static ArrayList<Point> getPair(ArrayList<String[]> s, int x, int y) {
        x = x + 1;
        y = y + 1;
        ArrayList<Point> k = new ArrayList<Point>();

        int a = (s.get(0)).length;
        for (int i = 0; i < a; i++) {

            if (s.get(y)[i].equals("")) {
                Point p = new Point((s.get(x))[i], 0);
                k.add(p);
                continue;
            }

            try { Double.parseDouble(s.get(y)[i]);
            } catch (java.lang.NumberFormatException e) { continue; }
            Point p = new Point((s.get(x))[i], Double.parseDouble(s.get(y)[i]));
            k.add(p);
        }
        return k;

    }


    public static void main(String args[]) throws FileNotFoundException {
        ArrayList<String[]> myList = getArray("Data/market-share.csv");
        ArrayList<Point> a = getPair(myList, 1, 10);
        System.out.println(a.toString());
        //System.out.println(((myList.get(0)))[1]);
        //System.out.println(Arrays.toString(myList.get(9)));


    }

}
