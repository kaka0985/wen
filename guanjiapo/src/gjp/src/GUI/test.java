package GUI;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    double fuzhuang1=0.0;
    double chifan1=0.0;
    double jiaotong1=0.0;
    double lijin1=0.0;
    double zhufang1=0.0;
    double gupiao1=0.0;
    double gongzi1=0.0;
    String s;
    ChartPanel frame11;
    String [][] date=new String[100][7];
        public  void show(){
            for(int i=0;i<100;i++){
                if(date[i][0]==null){
                    break;
                }
                for(int j=0;j<7;j++){
                    if(j==1) {
                     if(date[i][j].equals("支出")){
                         if(date[i][j+1].equals("服装支出")){

                             fuzhuang1=Double.parseDouble(date[i][j+2])+fuzhuang1;
                         }if(date[i][j+1].equals("吃饭支出")){

                             s=date[i][j+2];
                             chifan1=Double.parseDouble(s)+chifan1;
                         }if(date[i][j+1].equals("交通支出")){

                             jiaotong1=Double.parseDouble(date[i][j+2])+jiaotong1;
                         }if(date[i][j+1].equals("礼金支出")){

                             lijin1=Double.parseDouble(date[i][j+2])+lijin1;
                         }if(date[i][j+1].equals("住房支出")){

                             zhufang1=Double.parseDouble(date[i][j+2])+zhufang1;
                         }
                     } else{
                         if(date[i][j+1].equals("工资收入")){

                             gongzi1=Double.parseDouble(date[i][j+2])+gongzi1;
                         }if(date[i][j+1].equals("股票收入")){

                             gupiao1=Double.parseDouble(date[i][j+2])+gupiao1;
                         }
                     }
                    }
                }
            }
//
////            System.out.println("1");
////            JFrame jf = new JFrame();
////            JPanel panel1 = new JPanel();
////            System.out.println("11");
////
////            jf.setLayout(new FlowLayout());
////            jf.add(panel1);
////            jf.pack();
////            jf.setVisible(true);
////            System.out.println("11");
////            panel1.add(pi.pie());
//
        }
        public void show2(){
            Pie pie=new Pie();

            pie.fuzhuang1=fuzhuang1;
            pie.chifan1=chifan1;
            pie.jiaotong1=jiaotong1;
            pie.lijin1=lijin1;
            pie.zhufang1=zhufang1;
            pie.gupiao1=gupiao1;
            pie.gongzi1=gongzi1;
            pie.show();
        }
    }

