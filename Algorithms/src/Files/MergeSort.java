package Files;

public class MergeSort {

    public static final boolean INCREASING = true;
    public static final boolean DECREASING = false;

    /**
     * merge sort using Comparable array as input
     *
     * @param x - the input array containing scores of words that need to be sorted.
     */
    public static void sort(Comparable[] x) { sort(x, true); }
    public static void sort(Comparable[] x, boolean increasing) { sort(x, 0, x.length - 1, increasing); }
    public static void sort(Comparable[] x, int min, int max, boolean increasing) {
        //base case
        if (max <= min) {
            return;
        }

        //do the dividing
        int middlePoint = (max + min) / 2;
        sort(x, min, middlePoint, increasing);
        sort(x, middlePoint + 1, max, increasing);

        //combine
        merge(x, min, middlePoint, max, increasing);
        return;
    }//end sort merge

    public static void merge(Comparable[] a, int lo, int mid, int hi, boolean increasing) {  // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid + 1;

        Comparable[] aux = new Comparable[hi + lo + 1];
        for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)  // Merge back to a[lo..hi].
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if(compareValues(aux[j], aux[i], increasing)) a[k] = aux[j++];
//            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
    }

    public static <T extends Comparable<T>> boolean sorted(T[] a){ return sorted(a, true);}
    public static <T extends Comparable<T>> boolean sorted(T[] a, boolean increasing)
    {  	// Test whether the array entries are in order.
        //this loop runs through the entire array
        for(int i = 0; i < a.length-1; i++){
            //checks if the next element is smaller then itself, if it is it returns false
            if(increasing){ if(a[i].compareTo( a[i+1]) > 0) return false; }
            else if(a[i].compareTo(a[i+1]) < 0) return false;
        }
        return true;
    }

    public static boolean compareValues(Comparable firstValue, Comparable secondValue, boolean increasing){
        if(increasing) return less(firstValue, secondValue);
        else return greater(firstValue, secondValue);
    }

    private static boolean less(Comparable v, Comparable w)     { return v.compareTo(w) < 0; }
    private static boolean greater(Comparable v, Comparable w)  { return v.compareTo(w) > 0;}

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void printWords(Object[] words) {
        for (int c = 0; c < words.length; c++) {
            if (words[c] == null) continue;
            System.out.print(words[c].toString());
        }
        System.out.println();
    }

}//end class