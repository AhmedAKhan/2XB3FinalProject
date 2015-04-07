package testFiles;

import static org.junit.Assert.*;

import Files.Insertion;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.Test;

public class InsertionTest {
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
		//checking if the sorted method works
		Integer test1[] = {1,2,3,4,5};
		assertTrue(Insertion.sorted(test1));//the array is sorted, it should return true
		
		test1 = new Integer[]{3,2,1,5,7,5,9,7,5,2};
		assertTrue(!Insertion.sorted(test1)); // array is not sorted, the answer should be false
		
		test1 = new Integer[]{1,2,3,3,5,6,7,8};
		assertTrue(Insertion.sorted(test1));//the array is sorted, it should return true
		

		test1 = new Integer[]{1,2,3,3,5,2,7,8};
		assertTrue(!Insertion.sorted(test1));//the array is not sorted, it should return false
		
		//now that we know that the sorted function works we can use that to test the arrays
		
		assertTrue(!Insertion.sorted(array500));//originally the array is not sorted so the answer should be false
		Insertion.sort(array500);//sort the array
		assertTrue(Insertion.sorted(array500));//the sorted answer should be true
		
		//now we are going to sort the array5000 and check if it work
		assertTrue(!Insertion.sorted(array5000));
		Insertion.sort(array5000);
		assertTrue(Insertion.sorted(array5000));

        try {
            Integer[] numbers = new Integer[]{};
            Insertion.sort(numbers);
        }catch (Exception e){
            fail("attempting to sort an empty array resulted in an array");
        }

	}//end of the test sort method

}//end class method
