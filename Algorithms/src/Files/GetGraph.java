package Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class GetGraph {

    /*
        @parem dataset = Dataset(Olympic dataset)
           - assuming its in the format specified in the requirements
        @parem category = Category(Countries: Canada),
     */
    public GetGraph(String fileName, String category) {
        /*makes a bst
            - key = string
            - value = array of strings, containing all the values of the line*/
        BinarySearchTree<String, String[]> bst = new BinarySearchTree<String, String[]>();

        /*get all the data in that category*/
        Scanner input = null;
        try {
            input = new Scanner(new File(fileName));
            //find the heading
            String[] headings = input.nextLine().split(",");
            System.out.println("headings: " + Arrays.deepToString(headings));

            int categoryIndex;
            for(categoryIndex = 0; categoryIndex < headings.length; categoryIndex++)
                if(category.equals(headings[categoryIndex])) break;

            while(input.hasNext()){
                String[] line = input.nextLine().split(",");
//                System.out.println("line: " + line);
                bst.put(line[categoryIndex], line);
            }
            System.out.println("printing the bst");
            System.out.println(bst.toString());
            System.out.println("done printing the bst");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(input != null) input.close();
        }
//        return its
    }

    public static void main(String[] args){
        GetGraph g = new GetGraph("./Data/searchEngineMarketShare.csv", "bing");
    }

}
