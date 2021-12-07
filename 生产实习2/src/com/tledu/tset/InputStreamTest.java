package com.tledu.tset;

import java.io.*;

public class InputStreamTest {
    public static void main(String[] args) throws Exception {
        File file=new File("hello2.txt");
        InputStream inputStream=new FileInputStream(file);
//        int i=inputStream.read();
//        System.out.println((char)i);
//        int i2=inputStream.read();
//        System.out.println(i2);
//        int i3=inputStream.read();
//        System.out.println(i3);
//        int i4=inputStream.read();
//        System.out.println(i4);
//        int i=0;
//         while ((i=inputStream.read())!=-1){
//             System.out.println((char)i);
//         }
         byte[] b=new byte[10];
//         int read = inputStream.read(b);
//        System.out.println(read);
//        for (byte b1 : b) {
//            if(b1!=0){
//            System.out.println(b1);
//        }else {
//                break;
//            }
        int read=inputStream.read(b,0,5);
        System.out.println(read);
        inputStream.close();
        }



    }

