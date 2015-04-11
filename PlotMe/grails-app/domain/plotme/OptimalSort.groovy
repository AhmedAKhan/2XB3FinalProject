package plotme

public class OptimalSort
{

    public static void main(String[] args){
//        Integer[] arr = [0,5,6,3,2,64,7,6];
//        sort(arr);
//        System.out.println("string: " + Arrays.deepToString(arr));
    }

    private static final int MAXINSERTIONSORT = 15;
    //this type is used to tell the sorting algorithm which order to sort the function
    public static enum type{
        INCREASING,
        DECREASING
    }
    /**
     * @param size = the size of the array you want to make
     */
    private static Integer[] randomArrayOfSize(int size){
        Random r = new Random();
        Integer[] data = new Integer[size];
        for(int i = 0; i < size; i++){
            data[i] = r.nextInt(Integer.MAX_VALUE);
        }
        return data;
    }

    private static void timesForDifferentMaxInsertionSortValues(){
        int maxInsertionSort = 1;
        double fastestTime = Double.MAX_VALUE;
        int bestMaxInsertionSort = -1;

        final int sizeOfArray = 1000;

        for(int i = 0; i < sizeOfArray; i++){

            final int numberOfTries = 10000;

            long totalTime = 0;
            for(int j = 0; j < numberOfTries; j++) {
                Integer[] data = randomArrayOfSize(sizeOfArray);

                long currentTime = System.nanoTime();
                sort(data);
                currentTime = System.nanoTime() - currentTime;
                totalTime += currentTime;
            }

            long currentAverageTime = totalTime/numberOfTries;
            if(currentAverageTime < fastestTime) {
                fastestTime = currentAverageTime;
                bestMaxInsertionSort = maxInsertionSort;
            }

            System.out.println(" currentAverageTime: " + currentAverageTime + " : maxInsertionSort: " + maxInsertionSort);
            maxInsertionSort++;
        }//end while

        System.out.println("the best value for maxInsertionSort was: " + bestMaxInsertionSort);
    }

    /**
     * @param comparables = sorts this array
     * @param <T> = could by any type as long as it can be compared to itself
     */
    public static <T extends Comparable<T>> void sort(T[] comparables){ sort(comparables, 0, comparables.length-1);}
    public static <T extends Comparable<T>> void sort(T[] comparables, type sortType){ sort(comparables, 0, comparables.length.intValue()-1, sortType); }
    /**
     * @param comparables = an array of numbers that can be sorted
     * @param min = the minimum element number you want to be sorted, including the min element
     * @param max = the maximum element number you want to be sorted, including the max'th element
     */
    public static <T extends Comparable<T>> void sort(T[] comparables, int min, int max) { sort(comparables, 0, comparables.length-1, type.INCREASING); }
    public static <T extends Comparable<T>> void sort(T[] comparables, int min, int max, type sortType) {
        //use insertion sort if the array is to small
        if(max-min < MAXINSERTIONSORT) {
            Insertion.sort(comparables, min, max, sortType);
            return;
        }
        //do the dividing
        int middlePoint = (max+min)/2;
        sort(comparables, min, middlePoint, sortType);
        sort(comparables, middlePoint+1, max, sortType);


        merge(comparables, min, middlePoint, max, sortType);//combine
    }
    public static void merge(Comparable[] a, int lo, int mid, int hi){  }
    public static void merge(Comparable[] a, int lo, int mid, int hi, type sortType)
    {  // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid+1;
        Comparable[] aux = new Comparable[hi+lo+1];
        for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)  // Merge back to a[lo..hi].
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi )              a[k] = aux[i++];
            else if (compareValues(aux[j], aux[i], sortType)) a[k] = aux[j++];
//            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
    }

    private static boolean compareValues(Comparable v, Comparable w, type sortType){
        if(sortType == type.INCREASING) return less(v,w);
        else return greater(v,w);
    }

    private static boolean greater(Comparable v, Comparable w)
    {  return v.compareTo(w) > 0;  }
    private static boolean less(Comparable v, Comparable w)
    {  return v.compareTo(w) < 0;  }
    private static void exch(Comparable[] a, int i, int j)
    {  Comparable t = a[i]; a[i] = a[j]; a[j] = t;  }

    public static <T extends Comparable<T>> boolean sorted(T[] comparables) { return sorted(comparables, type.INCREASING); }
    public static <T extends Comparable<T>> boolean sorted(T[] comparables, type sortType) { return sorted(comparables, 0, comparables.length-1, sortType); }
    public static <T extends Comparable<T>> boolean sorted(T[] comparables, int min, int max, type sortType){
        // Test whether the array entries are in order.
        //this loop runs through the entire array
        for(int i = min; i < max-1; i++){
            //checks if the next element is smaller then itself, if it is it returns false
//            if(a[i].compareTo( a[i+1]) > 0) return false;
            if(sortType == type.INCREASING){ if(comparables[i].compareTo( comparables[i+1]) > 0) return false; }
            else if(comparables[i].compareTo(comparables[i+1]) < 0) return false;
        }
        return true;
    }

    public static void printArray(Object[] words){
        for(int c = 0; c < words.length; c++){
            if(words[c] == null) continue;
            System.out.print(words[c].toString() + ", ");
        }
        System.out.println(" end ");
    }

}