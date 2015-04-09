package testFiles;

import Files.OptimalSort;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class OptimalSortTest {

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
        while (st1.hasMoreTokens()) {
            array500[i] = new Integer(Integer.parseInt(st1.nextToken()));
            i++;
        }

        //use a stringtokenizer object to parse the string line2 into Integers,
        //then store them in array5000
        StringTokenizer st2 = new StringTokenizer(line2);
        int j = 0;
        while (st2.hasMoreTokens()) {
            array5000[j] = new Integer(Integer.parseInt(st2.nextToken()));
            j++;
        }
    }//end setup

    @Test public void testLargeDataSets() {

        Double[] test = new Double[]{0.0 , 0.0 , 0.0 , 0.0 , 0.0, 40.9, 0.0, 19.6, 27.4, 12.1};
        OptimalSort.printArray(test);
        OptimalSort.sort(test, OptimalSort.DECREASING);
        OptimalSort.printArray(test);

//        OptimalSort op = new OptimalSort();
//        if(op.setUp(true).length != 500) fail("the setup function is not returning a size of 500 when given true, it is returning a size of : " + op.setUp(true).length);
//        if(op.setUp(false).length != 5000) fail("The setup function is not returning an array of size 5000 when given the input false, it is given a size of: " + op.setUp(false).length);

        if(OptimalSort.randomArrayOfSize(100).length != 100) fail("array does not have the proper size expected 100 but got : " + OptimalSort.randomArrayOfSize(100).length);
        if(OptimalSort.randomArrayOfSize(50).length != 50) fail("array does not have the proper size expected 50 but got : " + OptimalSort.randomArrayOfSize(50).length);

        //checking if the sorted method works
        Integer test1[] = {1, 2, 3, 4, 5};
        assertTrue(OptimalSort.sorted(test1));//the array is sorted, it should return true

        test1 = new Integer[]{3, 2, 1, 5, 7, 5, 9, 7, 5, 2};
        assertTrue(!OptimalSort.sorted(test1)); // array is not sorted, the answer should be false

        test1 = new Integer[]{1, 2, 3, 3, 5, 6, 7, 8};
        assertTrue(OptimalSort.sorted(test1));//the array is sorted, it should return true

        test1 = new Integer[]{1, 2, 3, 3, 5, 2, 7, 8};
        assertTrue(!OptimalSort.sorted(test1));//the array is not sorted, it should return false

        test1 = new Integer[]{4,5,3,2,6,3,1,5,01,29,43,6,52};
        assertTrue(!OptimalSort.sorted(test1, OptimalSort.DECREASING));
        OptimalSort.sort(test1, OptimalSort.DECREASING);
        assertTrue(OptimalSort.sorted(test1, OptimalSort.DECREASING));

        //now that we know that the sorted function works we can use that to test the arrays

        assertTrue(!OptimalSort.sorted(array500));//originally the array is not sorted so the answer should be false
        OptimalSort.sort(array500);//sort the array
        assertTrue(OptimalSort.sorted(array500));//the sorted answer should be true

        //now we are going to sort the array5000 and check if it work
        assertTrue(!OptimalSort.sorted(array5000));
        OptimalSort.sort(array5000);

        assertTrue(OptimalSort.sorted(array5000));

    }//end of the test sort method
}//end class method
