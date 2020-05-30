package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        int sizelist = 20083;
        String fileName = "c19cases";//датасет випадків ковід 19
        File file = new File(fileName);
        //створмо ереї
        ArrayList<String> date = new ArrayList<>();
        int[] day = new int[sizelist];
        int[] month = new int[sizelist];
        int[] year = new int[sizelist];
        int[] cases = new int[sizelist];
        int[] deaths = new int[sizelist];
        String[] country = new String[sizelist];
        String[] continent = new String[sizelist];

        try {
            Scanner inpStream = new Scanner(file);
            inpStream.next(); //пропускаємо 1ий рядок бо це просто назви стовпчиків
            int count = 0;
            while (inpStream.hasNext()) {

                String data = inpStream.next(); //читаємо рядок
                String[] values = data.split(","); //створюємо тимчасовий масив, ділячи рядок через коми. Таким чином, наприклад value[0] - це дата у вигляді дд/мм/рррр
               //додаємо типу в кожен ерей відповідну інфу
                try {
                    day[count] = Integer.valueOf(values[1]);
                    month[count] = (Integer.valueOf(values[2]));
                    cases[count] = (Integer.valueOf(values[4]));
                    deaths[count] = (Integer.valueOf(values[5]));
                    country[count] = (values[6]);
                    continent[count] = (values[10]);
                    count++;
                } catch (Exception e){
                    e.printStackTrace();
                }



                //System.out.println(data);
            }
            inpStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //в якості тесту я хотів порахувати к-сть смертей в Україні за весь час
        int sumDeaths = 0;
        for (int i = 0; i < sizelist; i++) {
            if (country[i].equalsIgnoreCase("Ukraine")) {
                System.out.println(country[i]);
                System.err.println(deaths[i]);
                sumDeaths = sumDeaths + deaths[i];
            }
        }
        System.err.println(sumDeaths);//не працюэ


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
