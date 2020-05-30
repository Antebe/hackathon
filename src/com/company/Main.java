package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {


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
                switch (j){
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
        int deathTotalUkr = 0;
        for (int i = 1; i < sizelist; i++) {
            if(country[i].equals("Ukraine")){
                deathTotalUkr = deaths[i] + deathTotalUkr;
                System.out.println(day[i] + "/" + month[i] + " " + deaths[i]);
            }
        }
        System.out.println(deathTotalUkr);

        List<String[]> air = get("waqi-covid19-airqualitydata-2020.csv");
        int sizea = air.size();
        String[] dates = new String[sizea];
        String[] countrycode = new String[sizea];
        String[] gas = new String[sizea];
        //все ще пишу

    }



//ініціалізуємо датасет якості повітря




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


}
