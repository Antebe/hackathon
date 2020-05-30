package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String fileName = "c19cases";//датасет випадків ковід 19
        File file = new File(fileName);
        //створмо ерейлісти
        ArrayList<String> date = new ArrayList<>();
        ArrayList<Integer> day = new ArrayList<>();
        ArrayList<Integer> month = new ArrayList<>();
        ArrayList<Integer> year = null;
        ArrayList<Integer> cases = new ArrayList<>();
        ArrayList<Integer> deaths = new ArrayList<>();
        ArrayList<String> country = new ArrayList<>();
        ArrayList<String> continent = new ArrayList<>();

        try {
            Scanner inpStream = new Scanner(file);
            inpStream.next(); //пропускаємо 1ий рядок бо це просто назви стовпчиків
            while (inpStream.hasNext()) {
                String data = inpStream.next(); //читаємо рядок
                String[] values = data.split(","); //створюємо тимчасовий масив, ділячи рядок через коми. Таким чином, наприклад value[0] - це дата у вигляді дд/мм/рррр
                 //додаємо типу в кожен ерей відповідну інфу
                day.add(Integer.valueOf(values[1]));
                month.add(Integer.valueOf(values[2]));
                cases.add(Integer.valueOf(values[4]));
                deaths.add(Integer.valueOf(values[5]));
                country.add(values[6]);
                //хуй пойми чому, але воно дає Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 1
                //	at com.company.Main.main(Main.java:33)

                //System.out.println(data);
            }
            inpStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //в якості тесту я хотів порахувати к-сть смертей 20.04 по всьому світу
        int sumDeaths = 0;
        for (int i = 0; i < date.size(); i++) {
            if (day.get(i) == 20 && month.get(i) == 4) {
                sumDeaths = sumDeaths + deaths.get(i);
            }
        }
        System.out.println(sumDeaths);


        //nihuya

//        BufferedImage img;
//
//        {
//            try {
//                img = ImageIO.read(new File("2016-europe.jpg"));
//                int w = img.getWidth();
//                int h = img.getHeight();
//                double[][] pixels = new double[w][h];
//                int[][] lattit = new int[w][h];
//                int[][] longit = new int[w][h];
//                 for( int i = 0; i < w; i++ ){
//                    for( int j = 0; j < h; j++ ){
//                        int temp = img.getRGB( i, j );
//                        int red = (temp & 0x00ff0000) >> 16;
//                        pixels[i][j] = red;
//                        lattit[i][j] = 67 - j*(33/h);
//                        longit[i][j] = 7 + i*(33/w);
//                    }
//                }
//
//                System.out.println(pixels[longi(32, h)][latt(50, w)]);
//
//
//
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


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


}
