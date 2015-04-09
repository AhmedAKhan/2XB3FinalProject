package testFiles;

import static org.junit.Assert.*;

import Files.MergeSort;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.Test;

public class MergesortTest {
    //declaration
    private Integer array500[];
    private Integer array5000[];

    @Before
    public void setUp() throws Exception {
        //initialisation
        array500 = new Integer[500];
        array5000 = new Integer[5000];

        //Use scanner to read in the lines of input as strings
        Scanner sc = new Scanner(new File("./src/testFiles/refractorTest.txt"));
        String line1 = sc.nextLine();
        String line2 = sc.nextLine();
        sc.close();//close the stream

        //use a stringtokenizer object to parse the string line1 into Integers,
        //then store them in array500
        StringTokenizer st1 = new StringTokenizer(line1);
        int i = 0;
        while(st1.hasMoreTokens()){
            array500[i] = new Integer(Integer.parseInt(st1.nextToken()));
            i++;
        }

        //use a stringtokenizer object to parse the string line2 into Integers,
        //then store them in array5000
        StringTokenizer st2 = new StringTokenizer(line2);
        int j = 0;
        while(st2.hasMoreTokens()){
            array5000[j] = new Integer(Integer.parseInt(st2.nextToken()));
            j++;
        }
    }//end setup

    @Test
    public void testSort() {
        // ------------- started testing sorted ----------------------
        //checking if the sorted method works
        Integer test1[] = {1,2,3,4,5};
        assertTrue(MergeSort.sorted(test1));//the array is sorted, it should return true

        test1 = new Integer[]{3,2,1,5,7,5,9,7,5,2};
        assertTrue(!MergeSort.sorted(test1)); // array is not sorted, the answer should be false

        test1 = new Integer[]{1,2,3,3,5,6,7,8};
        assertTrue(MergeSort.sorted(test1));//the array is sorted, it should return true

        test1 = new Integer[]{1,2,3,3,5,2,7,8};
        assertTrue(!MergeSort.sorted(test1));//the array is not sorted, it should return false
        // ------------- started testing sorted ----------------------

        // ------------- started testing sort ----------------------

        assertTrue(!MergeSort.sorted(test1, MergeSort.DECREASING));
        test1 = new Integer[]{1, 5, 7, 5, 3, 2, 2};
        MergeSort.sort(test1, MergeSort.DECREASING);
        assertTrue(MergeSort.sorted(test1, MergeSort.DECREASING));
        //now that we know that the sorted function works we can use that to test the arrays

        assertTrue(!MergeSort.sorted(array500));//originally the array is not sorted so the answer should be false
        MergeSort.sort(array500);//sort the array
        assertTrue(MergeSort.sorted(array500));//the sorted answer should be true

        //now we are going to sort the array5000 and check if it work
        assertTrue(!MergeSort.sorted(array5000));
        MergeSort.sort(array5000);
        assertTrue(MergeSort.sorted(array5000));
    }//end of the test sort method

}//end class method
