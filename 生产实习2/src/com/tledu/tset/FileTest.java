package com.tledu.tset;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest {
    public static void main(String[] args) throws IOException {
        File file=new File("hello2.txt");
//        File file2=new File("hello2.txt");
//        file.createNewFile();
//        file.renameTo(file2);

         boolean b=  file.exists();
        System.out.println(b);
        System.out.println(file.isHidden());
        boolean b1 = file.isDirectory();
        System.out.println(b1);
        System.out.println(file.isFile());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());

        System.out.println(file.getAbsoluteFile());//绝对路径
        System.out.println(file.getPath());//相对路径
        System.out.println(file.getName());//文件名
        System.out.println(file.length());
        System.out.println(file.lastModified());
        Long l=1636787575719l;
        Date data=new Date(l);
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sd.format(data));
    }
}
