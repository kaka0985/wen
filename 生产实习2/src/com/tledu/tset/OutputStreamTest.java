package com.tledu.tset;

import java.io.*;

public class OutputStreamTest {
    public static void main(String[] args) throws Exception {
        OutputStream out=new FileOutputStream("hello2.txt");
//        out.write(97);
//        out.write(98);
//        out.write(99);
//        byte[] b2={97,98,99,100};
//        out.write(b2);
        String str="今天天气不错挺风和日丽的我骑着自行车就来到了河北省石家庄学院";
        byte[] bytes = str.getBytes();
        out.write(bytes);
        out.close();
        File file=new File("hello2.txt");
        InputStream inputStream=new FileInputStream(file);
        byte[] b=new byte[100];
         int read = inputStream.read(b);
        for (byte b1 : b) {
            if(b1!=0){
//            System.out.print((String)b1+" ");
        }else {
                break;
            }

    }}
}
