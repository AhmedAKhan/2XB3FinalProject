package plotme

class OptimalSort {

	final static int MAXINSERTIONSORT = 15;
	/**
	 * @param comparables = sorts this array
	 * @param <T> = could by any type as long as it can be compared to itself
	 */
	public static <T extends Comparable<T>> void sort(T[] comparables){ sort(comparables, 0, comparables.length-1);}
	/**
	 * @param comparables = an array of numbers that can be sorted
	 * @param min = the minimum element number you want to be sorted, including the min element
	 * @param max = the maximum element number you want to be sorted, including the max'th element
	 */
	public static <T extends Comparable<T>> void sort(T[] comparables, int min, int max) {
		//use insertion sort if the array is to small
		if(max-min < MAXINSERTIONSORT) {
			insertionSort(comparables, min, max);
			return;
		}
		//do the dividing
		int middlePoint = (max+min)/2;
		sort(comparables, min, middlePoint);
		sort(comparables, middlePoint+1, max);
		
		/* if this ever stops working uncomment these lines, and test it using this */
//        if(!sorted(comparables, min, middlePoint)) throw new Exception("the values from min: " + min + " middlePoint: " + middlePoint + " are not sorted yet");
//        if(!sorted(comparables, middlePoint+1, max)) throw new Exception("the values from min: " + (middlePoint+1) + " middlePoint: " + max + " are not sorted yet");
		merge(comparables, min, middlePoint, max);//combine
	}
	private static boolean greater(Comparable v, Comparable w)
	{  return v.compareTo(w) > 0;  }
	public static void merge(Comparable[] a, int lo, int mid, int hi)
	{  // Merge a[lo..mid] with a[mid+1..hi].
		int i = lo, j = mid+1;
		Comparable[] aux = new Comparable[hi+lo+1];
		for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
			aux[k] = a[k];
		for (int k = lo; k <= hi; k++)  // Merge back to a[lo..hi].
			if      (i > mid)              a[k] = aux[j++];
			else if (j > hi )              a[k] = aux[i++];
			else if (greater(aux[j], aux[i])) a[k] = aux[j++];
			else                           a[k] = aux[i++];
	}
	private static void exch(Comparable[] a, int i, int j)
	{  Comparable t = a[i]; a[i] = a[j]; a[j] = t;  }
	
	private static <T extends Comparable<T>> void insertionSort(T[] a) { insertionSort(a, 0, a.length-1);}
	private static <T extends Comparable<T>> void insertionSort(T[] a, int min, int max){
		// Sort a[] into increasing order.
//		for (int i = min+1; i <= max; i++)
//		{   // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..

			//it stores the current number to insert in the variable currentElement
//			T currentElement = a[i];
			
			for (int i = 1; i < a.length; i++) { 
				for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
					int temp = a[j - 1];
					a[j - 1] = a[j];
					a[j] = temp;
				}
			}
			//it creates a loop that runs from the end of the sorted array to the elment where we need to place the number
//			int j;
//			for(j = i-1; j > 0 && currentElement.compareTo(a[j]) > 0; j--){
//				a[j+1] = a[j];
//			}
			
			//adds the number that we needed to insert in its slot
//			a[j] = currentElement;
//			System.out.println("currentElement: " + currentElement +" i: " + i +" j: " + j +  " numbers: " + a);
//		}
	}
	
	
	
	
	
	
	
	public static void main(String[] args){
		System.out.println("1 this is a test print");
		
		Integer[] numbers = [2,8,5,9,1,6,2,7,8,3,4,6,34,1,87,9,43,10, 50,12,43,6,43];
		System.out.println("numbers: " + numbers);
		System.out.println("2 ");
		sort(numbers);
		System.out.println("3 ");
		System.out.println("numbers: " + numbers);
		
	}
	
}
