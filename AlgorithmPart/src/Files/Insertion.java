package Files;

public class Insertion
{
	private static <T extends Comparable<T>> boolean less(T v, T w)
    {  return v.compareTo(w) < 0;  }
    
	private static <T extends Comparable<T>> void exch(T[] a, int i, int j)
    {  T t = a[i]; a[i] = a[j]; a[j] = t;  }
    
    public static <T extends Comparable<T>> void show(T[] a)
    {  // Print the array, on a single line.
       for (int i = 0; i < a.length; i++)
          System.out.print(a[i] + " ");
       System.out.println();
    }
    
    public static <T extends Comparable<T>> boolean sorted(T[] a)
    {  	// Test whether the array entries are in order.
    	//this loop runs through the entire array
    	for(int i = 0; i < a.length-1; i++){
    		//checks if the next element is smaller then itself, if it is it returns false
    		if(a[i].compareTo( a[i+1]) > 0) return false;
    	}
    	return true;
    }
	
	public static <T extends Comparable<T>> void sort(T[] a) { sort(a, 0, a.length-1);}
    public static <T extends Comparable<T>> void sort(T[] a, int min, int max){
        // Sort a[] into increasing order.
        for (int i = min+1; i <= max; i++)
        {   // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..

            //it stores the current number to insert in the variable currentElement
            T currentElement = a[i];

            //it creates a loop that runs from the end of the sorted array to the elment where we need to place the number
            int j;
            for(j = i-1; j > min && currentElement.compareTo(a[j]) < 0; j--){
                a[j+1] = a[j];
            }

            //adds the number that we needed to insert in its slot
            a[j] = currentElement;
        }
    }

}
