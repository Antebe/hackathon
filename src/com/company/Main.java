package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.ListAdapter;

public class Main extends Application {

   public static Data dataseter = new Data();
public static void main(String[] args) {


//ініціалізуємо датасет випадків ковід 19
        List<String[]> c19cases = get("c19cases");
        int sizelist = c19cases.size();
        int[] day = new int[sizelist];
        int[] month = new int[sizelist];
        int[] cases = new int[sizelist];
        int[] deaths = new int[sizelist];
        String[] country = new String[sizelist];
        String[] continent = new String[sizelist];
        for (int i = 1; i < c19cases.size(); i++) {
            String[] strings = c19cases.get(i);
            for (int j = 0; j < strings.length; j++) {
                //System.out.print(strings[j] + " ");
                switch (j) {
                    case 1:
                        day[i] = Integer.parseInt(strings[j]);
                        break;
                    case 2:
                        month[i] = Integer.parseInt(strings[j]);
                        break;
                    case 4:
                        cases[i] = Integer.parseInt(strings[j]);
                        break;
                    case 5:
                        deaths[i] = Integer.parseInt(strings[j]);
                        break;
                    case 6:
                        country[i] = strings[j];
                        break;
                    case 10:
                        continent[i] = strings[j];
                        break;
                }

            }
            // System.out.println();
        }

        //ксть смертей в Україні
        int casesUkraine = 0;
        List<Integer> caseUkr = new ArrayList<Integer>();
        List<Integer> caseUkrDate = new ArrayList<Integer>();
        for (int i = 1; i < sizelist; i++) {
            if (country[i].equals("Russia")) { //set country
                casesUkraine = cases[i] + casesUkraine;
                caseUkr.add(cases[i]);
                caseUkrDate.add(newyear(month[i], day[i]));
                System.out.println(day[i] + "/" + month[i] + " " + cases[i]);
            }
        }
        System.out.println("Cases " + casesUkraine);
        int[] casesUkrArr = caseUkr.stream().mapToInt(d -> d).toArray();
        int[] casesUkrCal = caseUkrDate.stream().mapToInt(d -> d).toArray();
        System.out.println("res " + casesUkrArr[0] + " " + casesUkrCal[0]);
        dataseter.setCasesUkr(casesUkrArr);
        dataseter.setCasesDate(casesUkrCal);


//ініціалізуємо датасет якості повітря
        List<String[]> air = get("waqi-covid19-airqualitydata-2020.csv");
        int sizea = air.size();
        String[] airdates = new String[sizea];
        int[] airdays = new int[sizea];
        int[] airmonths = new int[sizea];
        int[] airyear = new int[sizea];
        String[] aircountrycode = new String[sizea];
        String[] city = new String[sizea];
        String[] airgas = new String[sizea];
        double[] airmax = new double[sizea];
        for (int i = 5; i < air.size(); i++) {
            String[] strings = air.get(i);
            for (int j = 0; j < strings.length; j++) {
                //System.out.print(strings[j] + " ");
                switch (j) {
                    case 0:
                        airdates[i] = (strings[j]);
                        String[] dates = airdates[i].split("-");//розбиваємо рррр/мм/дд на роки місяці та дні
                        airdays[i] = Integer.parseInt(dates[2]);
                        airmonths[i] = Integer.parseInt(dates[1]);
                        airyear[i] = Integer.parseInt(dates[0]);
                        break;
                    case 1:
                        aircountrycode[i] = (strings[j]);
                        break;
                    case 2:
                        city[i] = strings[j];
                    case 3:
                        airgas[i] = (strings[j]);
                        break;
                    case 8:
                        airmax[i] = Double.parseDouble(strings[j]);
                        break;
                }

            }
        }

        //Київ
       List<Double> data = new ArrayList<Double>();
       List<Integer> calendar = new ArrayList<Integer>();
        for (int i = 5; i < sizea; i++) {
            if (aircountrycode[i].equals("RU") && airgas[i].equals("no2")  && city[i].equals("Moscow")) { //set gas and city
                System.out.println("Current - " + airdates[i] + " " + airgas[i] + " " + airmax[i]);
                data.add(airmax[i]);
                calendar.add(newyear(airmonths[i], airdays[i]));//типу дні після Нового року
            }
        }
        //сортуємо
        multiSort(calendar, calendar, data);
        double[] dataarr = data.stream().mapToDouble(d -> d).toArray();
        int[] calarr = calendar.stream().mapToInt(d -> d).toArray();
        System.out.println("res " + dataarr[0] + " " + calarr[0]);
        dataseter.setCalendar(calarr);
        dataseter.setGas(dataarr);

        Application.launch(args);


    }








    public static <T extends Comparable<T>> void multiSort(
            final List<T> key, List<?>... lists){
        // Create a List of indices
        List<Integer> indices = new ArrayList<Integer>();
        for(int i = 0; i < key.size(); i++) {
            indices.add(i);
        }

        // Sort the indices list based on the key
        Collections.sort(indices, new Comparator<Integer>() {
            @Override public int compare(Integer i, Integer j) {
                return key.get(i).compareTo(key.get(j));
            }
        });

        // Create a mapping that allows sorting of the List by N swaps.
        // Only swaps can be used since we do not know the type of the lists
        Map<Integer,Integer> swapMap = new HashMap<Integer, Integer>(indices.size());
        List<Integer> swapFrom = new ArrayList<Integer>(indices.size()),
                swapTo   = new ArrayList<Integer>(indices.size());
        for (int i = 0; i < key.size(); i++) {
            int k = indices.get(i);
            while (i != k && swapMap.containsKey(k)) {
                k = swapMap.get(k);
            }

            swapFrom.add(i);
            swapTo.add(k);
            swapMap.put(i, k);
        }

        // use the swap order to sort each list by swapping elements
        for (List<?> list : lists)
            for (int i = 0; i < list.size(); i++)
                Collections.swap(list, swapFrom.get(i), swapTo.get(i));
    }


//    public static int longi(double l, int w){
//        l = (l-7)*w/(33) ;
//        return (int) l;
//    }
//
//    public static int latt(double l, int h){
//        l = (67 - l)*h/(33) ;
//        return (int) l;
//    }
    public static List<String[]> get(String filename) {
        List<String[]> data = new ArrayList<String[]>();
        String testRow;
        try {
            // Open and read the file
            BufferedReader br = new BufferedReader(new FileReader(filename));
            // Read data as long as it's not empty
            // Parse the data by comma using .split() method
            // Place into a temporary array, then add to List
            while ((testRow = br.readLine()) != null) {
                String[] line = testRow.split(",");
                data.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found " + filename);
        } catch (IOException e) {
            System.out.println("ERROR: Could not read " + filename);
        }
        return data;
    }

    public static int newyear(int x, int y){
        if(x==12 || x==11){
            x = -y - 30*(12-x);
        }else {
            if(x==1 || x==3 || x==5 || x==7 || x==8 || x==10){
                x = 31*(x-1) + y;
            }
            if(x==4 || x==6 || x==9 ){
                x = 30*(x-1) + y;
            }
            if(x==2){
                x = 29*(x-1) + y;
            }
        }
        return x;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("days after the New Year");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("no2 in Moscow x10");

        LineChart lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("no2");

        for (int i = 0; i < dataseter.getGas().length; i++) {
            if(dataseter.getCalendar()[i] < 160 && dataseter.getGas()[i] != 0){
                dataSeries1.getData().add(new XYChart.Data(dataseter.getCalendar()[i], dataseter.getGas()[i]*10));
                System.out.println(dataseter.getCalendar()[i] + " - " + dataseter.getGas()[i]);
            }

        }
        lineChart.getData().add(dataSeries1);

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("cases");

        for (int i = 0; i < dataseter.getCasesUkr().length; i++) {
            if(dataseter.getCasesUkr()[i] != 0){
                dataSeries2.getData().add(new XYChart.Data(dataseter.getCasesDate()[i], dataseter.getCasesUkr()[i]));
                System.out.println((dataseter.getCasesDate()[i] + " - " + dataseter.getCasesUkr()[i]));
            }

        }
        lineChart.getData().add(dataSeries2);


        VBox vbox = new VBox(lineChart);
        Scene scene = new Scene(vbox, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.setHeight(300);
        primaryStage.setWidth(1200);

        primaryStage.show();

    }
}
