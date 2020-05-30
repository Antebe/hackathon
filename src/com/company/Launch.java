package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Launch {
    public static void main(String[] args) {
        BufferedImage img;

        {
            try {
                img = ImageIO.read(new File("BlackMarble_2016_01deg.jpg"));
                int w = img.getWidth();
                int h = img.getHeight();
                double[][] pixels = new double[w][h];
                double[][] lattit = new double[w][h];
                double[][] longit = new double[w][h];
                for( int i = 0; i < w; i++ ){
                    for( int j = 0; j < h; j++ ){
                        int temp = img.getRGB( i, j );
                        int red = (temp & 0x00ff0000) >> 16;
                        pixels[i][j] = red;
                        System.out.println(pixels[i][j]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public static int longi(double l, int w){
        l = (l-7)*w/(33) ;
        return (int) l;
    }

    public static int latt(double l, int h){
        l = (67 - l)*h/(33) ;
        return (int) l;
    }
}

