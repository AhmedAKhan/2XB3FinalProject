package Files;

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
    public Analayzer(String dataset, String category){

        //get all the min, max, and all the quartile information
        QuartileInformation[]  graphInfo = getQuartileInformationOfData();

        int sums[] = new int[graphInfo.length];

        //sort it between all the graphs
        Integer[][] quartilInformation = new Integer[5][graphInfo.length];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < graphInfo.length; j++){
                quartilInformation[i][j] = graphInfo[j].quartileInformation[i];
            }
            //sort all the data
            OptimalSort.sort(quartilInformation[i]);

            //just makes the data easier to work with
            Integer currentArray[] = quartilInformation[i];

            //gets the quartile information
            int q1 = quartilInformation[i][currentArray.length/4];
            int q3 = quartilInformation[i][currentArray.length/4*3];
            int quartileRange = q3-q1;

            //now divide all the data by this number and add that to the sums
            for(int j = 0; j < quartilInformation.length; j++){
                //
            }


        }

        //now that its sorted we can find the min, max, and the quartile information


        //find the IQR values added together
        for(){

        }

        //chose the graph with the IQR

    }
    private QuartileInformation[] getQuartileInformationOfData(){
        return null;
    }
/*
    private class KeyValuePair implements Comparable<KeyValuePair> {
        private int value;
        private QuartileInformation key;

        @Override public int compareTo(KeyValuePair o) {
            if(value > o.value) return 1;
            if(value == o.value) return 0;
            return -1;
        }
    }*/
    private class QuartileInformation{
        private String name;
        private int[] quartileInformation;

        public QuartileInformation(int min, int q1, int q2, int q3, int max){ this(new int[]{min, q1, q2, q3, max}); }
        public QuartileInformation(int[] quartileInformation){ this.quartileInformation = quartileInformation; }
    }

    public static void main(String[] args){

    }

}