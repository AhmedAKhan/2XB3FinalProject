package Files;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * This program's
 * input:
 *
 *      Input1: dataset will be the dataset given as a csv, input argument will be a string referring to the path
 *      Input2: category will be category where it will chose the best data
 *      Ouput: will be the name of the graph that it should display
 *
 */
public class Analayzer {

    /**
     * @param dataset = the string of the csv file
     * @param category = is the category of which column to chose from
     */

    /*
    Steps for this part of the software
        - get the min, median, and all the quartile information
        - Sort it between all the graphs
        - Find the value’s IQR values added together
        - Choose the graph with the highest IQR

     */
    public Analayzer(){

    }

    public QuartileInformation analyzeGraph(ArrayList<Point> dataset){
        //get all the min, max, and all the quartile information
        QuartileInformation[]  graphInfo = getQuartileInformationOfData(dataset, dataset);
        QuartileInformation oddestGraph = getOddestGraph(graphInfo);

        return oddestGraph;
    }

    /**
     *
     * @param graphInfo: the graph info will be the information of the graph given in an array of quartile information obejcts,
     * @return : the quartile information object of the weirdest graph
     */
    public QuartileInformation getOddestGraph(QuartileInformation[] graphInfo){
        int currentHighestValue = Integer.MIN_VALUE;
        QuartileInformation oddestGraph = null;

        //sort it between all the graphs
        Integer[] quartilInformation = new Integer[graphInfo.length];
        for(int quartileCounter = 0; quartileCounter < 5; quartileCounter++){
            for(int index = 0; index < graphInfo.length; index++){
                quartilInformation[index] = graphInfo[index].quartileInformation[quartileCounter];
            }
            //sort all the data
            OptimalSort.sort(quartilInformation);

            //gets the range of the quartile
            int quartileRange = getQuartileRange(quartilInformation);

            //adds the IQR value to the quartileInformation function
            for(int index = 0; index < graphInfo.length; index++ ){
                graphInfo[index].value += graphInfo[index].quartileInformation[quartileCounter]/quartileRange;

                //the second case should never be executed to be true in practice but i kept it here to ensure the system is robust
                if(graphInfo[index].value > currentHighestValue || oddestGraph == null){
                    currentHighestValue = graphInfo[index].value;
                    oddestGraph = graphInfo[index];
                }//end if statement
            }// for loop
        }//end for loop

        return oddestGraph;
    }

    public int getQuartileRange(Integer[] data){

        //gets the quartile information
        int min = data[0];
        int q1 = data[data.length/4];
        int q2 = data[data.length/2];
        int q3 = data[data.length/4*3];
        int quartileRange = q3-q1;
        int max = data[data.length-1];

        return quartileRange;//new KeyValuePair[]{new KeyValuePair(min, parent), new KeyValuePair(q1, parent), new KeyValuePair(q2, parent), new KeyValuePair(q3, parent), new KeyValuePair(max, parent)};
    }

    public class ChartInformation{
        public ArrayList independentVariable;
        public ArrayList dependentVariable;
        public ChartInformation(ArrayList ind, ArrayList dep){independentVariable = ind; dependentVariable = dep;};
    }


    /**
     * @parem it will have some input
     *
     *
     * @return This will return an array of quartile information
     */
    private QuartileInformation[] getQuartileInformationOfData(ArrayList<Point> dataset){


        return null;
    }

    private class KeyValuePair implements Comparable<KeyValuePair> {
        private int value;
        private String key;

        public KeyValuePair(int value, String key){ this.value = value; this.key = key; }
        @Override public int compareTo(KeyValuePair o) {
            if(value > o.value) return 1;
            if(value == o.value) return 0;
            return -1;
        }
    }


    /**
     * quartileInformation = is an array of 5 elements that represents the min, q1, q2, q3, max of the data set
     * name = this is the name of the graph, the heading to show what the data means, Programically it is used to tell the objects apart
     * value = this value represents how different this graph is from the rest
     */
    private class QuartileInformation{
        private String name;        //this is the name of the graph, the heading to show what the data means, Programically it is used to tell the objects apart
        private int[] quartileInformation;  ///stores all the quartile information
        private int value;          //this value represents how different this graph is from the rest

        public QuartileInformation(int min, int q1, int q2, int q3, int max){ this(new int[]{min, q1, q2, q3, max}); }
        public QuartileInformation(int[] quartileInformation){ this.quartileInformation = quartileInformation; }
    }

    @Test public void test(){
        Analayzer analyzer= new Analayzer();

        QuartileInformation[] arr = new QuartileInformation[5];
        for(int i= 0; i < arr.length; i++){
//            arr[i] =
        }
        analyzer.getOddestGraph(arr);
    }

    public static void main(String[] args){

    }

}