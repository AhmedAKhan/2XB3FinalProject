package Files;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class Analayzer {

    /**
     * This program's
     * input:
     * Input1: dataset will be the dataset given as a csv, input argument will be a string referring to the path
     * Ouput: will be the name of the graph that it should display
     * *
     */
    /*
    Steps for this part of the software
        - get the min, median, and all the quartile information
        - Sort it between all the graphs
        - Find the value’s IQR values added together
        - Choose the graph with the highest IQR

     */
    public Analayzer() {

    }


    /**
     * @param dataset = the string of the csv file
     */
    public QuartileInformation analyzeGraph(ArrayList<Point[]> dataset) {
        //get all the min, max, and all the quartile information
        QuartileInformation[] graphInfo = getQuartileInformationOfData(dataset);
        QuartileInformation oddestGraph = getOddestGraph(graphInfo);

        return oddestGraph;
    }

    /**
     * @param graphInfo: the graph info will be the information of the graph given in an array of quartile information obejcts,
     * @return : the quartile information object of the weirdest graph
     */
    public QuartileInformation getOddestGraph(QuartileInformation[] graphInfo) {
        int currentHighestValue = Integer.MIN_VALUE;
        QuartileInformation oddestGraph = null;

        //sort it between all the graphs
        Double[] quartilInformation = new Double[graphInfo.length];
        for (int quartileCounter = 0; quartileCounter < 5; quartileCounter++) {
            for (int index = 0; index < graphInfo.length; index++) {
                quartilInformation[index] = graphInfo[index].quartileInformation[quartileCounter];
            }
            //sort all the data
            OptimalSort.sort(quartilInformation);

            //gets the range of the quartile
            double quartileRange = getQuartileRange(quartilInformation);

            //adds the IQR value to the quartileInformation function
            for (int index = 0; index < graphInfo.length; index++) {
                graphInfo[index].value += graphInfo[index].quartileInformation[quartileCounter] / quartileRange;

                //the second case should never be executed to be true in practice but i kept it here to ensure the system is robust
                if (graphInfo[index].value > currentHighestValue || oddestGraph == null) {
                    currentHighestValue = graphInfo[index].value;
                    oddestGraph = graphInfo[index];
                }//end if statement
            }// for loop
        }//end for loop

        return oddestGraph;
    }

    /**
     * @param data = a given data set
     *             return the range of the quartile
     * @return
     */
    public double getQuartileRange(Double[] data) {

        //gets the quartile information
        double min = data[0];
        double q1 = data[data.length / 4];
        double q2 = data[data.length / 2];
        double q3 = data[data.length / 4 * 3];
        double quartileRange = q3 - q1;
        double max = data[data.length - 1];

        return quartileRange;//new KeyValuePair[]{new KeyValuePair(min, parent), new KeyValuePair(q1, parent), new KeyValuePair(q2, parent), new KeyValuePair(q3, parent), new KeyValuePair(max, parent)};
    }

    /**
     *
     */
    public class ChartInformation {
        public ArrayList independentVariable;
        public ArrayList dependentVariable;

        public ChartInformation(ArrayList ind, ArrayList dep) {
            independentVariable = ind;
            dependentVariable = dep;
        }

        ;
    }


    /**
     * @return This will return an array of quartile information
     * @parem it will have some input
     */
    private QuartileInformation[] getQuartileInformationOfData(ArrayList<Point[]> dataset) {
        QuartileInformation[] dataInformation = new QuartileInformation[dataset.size()];
        for (int i = 0; i < dataset.size(); i++) {
            Point[] sortedPoints = dataset.get(i);
            OptimalSort.sort(sortedPoints);
            double min = sortedPoints[0].getValue();
            double q1 = sortedPoints[sortedPoints.length / 4].getValue();
            double q2 = sortedPoints[sortedPoints.length / 2].getValue();
            double q3 = sortedPoints[sortedPoints.length * 3 / 4].getValue();
            double max = sortedPoints[sortedPoints.length - 1].getValue();
            QuartileInformation currentDataSet = new QuartileInformation(min, q1, q2, q3, max);

        }

        return null;
    }

    /**
     * this is the type everything is stored on
     */
    private class KeyValuePair implements Comparable<KeyValuePair> {
        private int value;
        private String key;

        public KeyValuePair(int value, String key) {
            this.value = value;
            this.key = key;
        }

        @Override
        public int compareTo(KeyValuePair o) {
            if (value > o.value) return 1;
            if (value == o.value) return 0;
            return -1;
        }
    }

    /**
     * quartileInformation = is an array of 5 elements that represents the min, q1, q2, q3, max of the data set
     * name = this is the name of the graph, the heading to show what the data means, Programically it is used to tell the objects apart
     * value = this value represents how different this graph is from the rest
     */
    private class QuartileInformation {
        private String name;        //this is the name of the graph, the heading to show what the data means, Programically it is used to tell the objects apart
        private double[] quartileInformation;  ///stores all the quartile information
        private int value;          //this value represents how different this graph is from the rest

        public QuartileInformation(double min, double q1, double q2, double q3, double max) {
            this(new double[]{min, q1, q2, q3, max});
        }

        public QuartileInformation(double[] quartileInformation) {
            this.quartileInformation = quartileInformation;
        }
    }


    @Test
    public void test() {
        Analayzer analyzer = new Analayzer();

        QuartileInformation[] arr = new QuartileInformation[5];
        arr[0] = new QuartileInformation();
        arr[1] = new QuartileInformation();
        arr[2] = new QuartileInformation();
        arr[3] = new QuartileInformation();
        arr[4] = new QuartileInformation();
        analyzer.getOddestGraph(arr);
    }

    public static void main(String[] args) {

    }

}