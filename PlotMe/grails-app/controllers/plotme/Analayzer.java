package plotme;

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
//     * @param dataset = the string of the csv file
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

        //print all the values
        for(int i =0; i < quartilInformation.length; i++){
            System.out.println("i: " + i + " value: " + graphInfo[i].value);
        }

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


            System.out.println("sortedPoints.length/2: " + sortedPoints.length/2 + " sortedPoints.length/2+1: " + (sortedPoints.length/2+1));
            System.out.println("sortedPoints.length/2-1: " + sortedPoints[sortedPoints.length/2-1] + " sortedPoints.length/2" + sortedPoints[(sortedPoints.length/2)]);
            double q2;// = sortedPoints[sortedPoints.length / 2].getValue();
            if(sortedPoints.length%2 == 0 ) q2 = (sortedPoints[sortedPoints.length/2-1].getValue() + sortedPoints[sortedPoints.length/2].getValue())/2;
            else q2 = sortedPoints[sortedPoints.length / 2].getValue();

            double q3 = sortedPoints[sortedPoints.length * 3 / 4].getValue();
            double max = sortedPoints[sortedPoints.length - 1].getValue();
            dataInformation[i] = new QuartileInformation(min, q1, q2, q3, max);
        }

        return dataInformation;
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

        public QuartileInformation(double min, double q1, double q2, double q3, double max) { this(new double[]{min, q1, q2, q3, max}); }
        public QuartileInformation(double[] quartileInformation) { this.quartileInformation = quartileInformation; }

        @Override public String toString(){
            String s = "";
            s += "name: " + name + " value: " + value + " quartileInformation: ";
            for(int i = 0; i < quartileInformation.length; i++){ s+= " q"+i+": " + quartileInformation[i]; }
            return s;
        }
    }


//    @/*Test
//    public void test() {
//        Analayzer analyzer = new Analayzer();
//
//        QuartileInformation[] arr = new QuartileInformation[5];
//        arr[0] = new QuartileInformation(0.04, 0.36, 1.36, 14.98, 57.24);
//        arr[1] = new QuartileInformation(0.04, 0.32, 1.26, 13.98, 57.46);
//        arr[2] = new QuartileInformation(0.08, 0.26, 1.25, 13.37, 56.62);
//        arr[3] = new QuartileInformation(0.07, 0.3, 1.16, 13.37, 56.89);
//        arr[4] = new QuartileInformation(0, 1, 3, 20, 70);
//        QuartileInformation a = analyzer.getOddestGraph(arr);
//        System.out.println("Quartile information: " + a);
//
//        ArrayList<Point[]> dataset = new ArrayList<Point[]>();
//        dataset.add(new Point[]{new Point("AOL",4.58),new Point("All the WEB",0.09),new Point("Alta Vista",0.74), new Point("Ask", 1.36), new Point("Excite",0.36), new Point("Google",57.24), new Point("Lycos", 0.04), new Point("MSN",14.98), new Point("Yahoo",17.19), new Point("others", 0)});
//        dataset.add(new Point[]{new Point("AOL",4.37),new Point("All the WEB",0.08),new Point("Alta Vista",0.71), new Point("Ask", 1.26), new Point("Excite",0.32), new Point("Google",57.46), new Point("Lycos", 0.04), new Point("MSN",14.98), new Point("Yahoo",18.43), new Point("others", 0)});
//        dataset.add(new Point[]{new Point("AOL",4.21),new Point("All the WEB",0.08),new Point("Alta Vista",0.66), new Point("Ask", 1.16), new Point("Excite",0.3 ), new Point("Google",56.89), new Point("Lycos", 0.07), new Point("MSN",14.98), new Point("Yahoo",19.66), new Point("others", 0)});
//        dataset.add(new Point[]{new Point("AOL",4.37),new Point("All the WEB",0.08),new Point("Alta Vista",0.61), new Point("Ask", 1.25), new Point("Excite",0.29), new Point("Google",56.62), new Point("Lycos", 0.09), new Point("MSN",14.98), new Point("Yahoo",19.95), new Point("others", 0)});
//        dataset.add(new Point[]{new Point("AOL",4.14),new Point("All the WEB",0.07),new Point("Alta Vista",0.56), new Point("Ask", 1.2 ), new Point("Excite",0.26), new Point("Google",57.36), new Point("Lycos", 0.09), new Point("MSN",14.98), new Point("Yahoo",19.49), new Point("others", 0)});
//        arr = analyzer.getQuartileInformationOfData(dataset);
//
//        System.out.println("arr: " + arr);
//        for(int i =0; i < arr.length; i++){
//            System.out.println("i: " + i + " arr[i]: " + arr[i]);
//        }
//        //tested the printed values with the values given by excel and it was correct
//
//    }

    public static void main(String[] args) {

    }

}