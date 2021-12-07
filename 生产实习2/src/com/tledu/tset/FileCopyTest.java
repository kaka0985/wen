package com.tledu.tset;

import java.io.*;

public class FileCopyTest {
    public static void main(String[] args) throws Exception {
        File file1=new File("D:\\idea\\生产实习2\\hello2.txt");
        File file2=new File("D:\\idea\\生产实习2\\b.txt");
        InputStream in= new FileInputStream(file1);//输出里面有东西
        OutputStream out=new FileOutputStream(file2);//输入往里输入东西里面本来没有东西

        int len=0;
        byte[] bytes=new byte[1024];
        while((len=in.read(bytes))!=-1){
            System.out.println(len);
            out.write(bytes,0,len);
        }
        out.flush();
        out.close();
        in.close();
    }
}
