package plotme

/**
 * Created by ahmed on 4/9/15.
 */
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

    public static <T extends Comparable<T>> void sort(T[] a) { sort(a, OptimalSort.type.INCREASING);}
    public static <T extends Comparable<T>> void sort(T[] a, OptimalSort.type increasing){ sort(a, 0, a.length-1, increasing); }
    public static <T extends Comparable<T>> void sort(T[] a, int min, int max){ sort(a, min, max, OptimalSort.type.INCREASING); }
    public static <T extends Comparable<T>> void sort(T[] a, int min, int max, OptimalSort.type sortType){
        // Sort a[] into increasing order.
        for (int i = min+1; i <= max; i++)
        {   // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..

            //it stores the current number to insert in the variable currentElement
            T currentElement = a[i];

            //it creates a loop that runs from the end of the sorted array to the element where we need to place the number
            int j;
            for(j = i-1; j >= min && needsExchange(currentElement, a[j], sortType) ; j--){
                a[j+1] = a[j];
            }

            //adds the number that we needed to insert in its slot
            a[j+1] = currentElement;
        }
    }

    private static <T extends Comparable<T>> boolean needsExchange(T currentElement, T secondElement, OptimalSort.type sortType){
        if(currentElement == null) return -1;
        if(secondElement == null) return 1;
        if(sortType == OptimalSort.type.INCREASING) return currentElement.compareTo(secondElement) < 0;
        else return currentElement.compareTo(secondElement) > 0;
    }

}
