package GUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Pie {
    test t=new test();
    ChartPanel frame1;


    double fuzhuang1=0.0;
    double chifan1=0.0;
    double jiaotong1=0.0;
    double lijin1=0.0;
    double zhufang1=0.0;
    double gupiao1=0.0;
    double gongzi1=0.0;


    ChartPanel frame11;
    public void show() {
        JFrame jf = new JFrame();
        jf.setSize(1200,500);
        JPanel panel1 = new JPanel();
        JPanel panel2=new JPanel();
        jf.setLayout(new FlowLayout());
        jf.setVisible(true);
        JTextArea area=new JTextArea(1,10);
        JTextArea area1=new JTextArea(1,10);
            DefaultPieDataset dataset1 = new DefaultPieDataset();
            dataset1.setValue("服装支出", fuzhuang1);
            dataset1.setValue("吃饭支出", chifan1);
            System.out.println(chifan1);
            dataset1.setValue("交通支出", jiaotong1);
            dataset1.setValue("礼金支出", lijin1);
            dataset1.setValue("其他支出", zhufang1);
            DefaultPieDataset dataset = new DefaultPieDataset();
            dataset.setValue("股票收入", gupiao1);
            dataset.setValue("工资收入", gongzi1);
            DefaultPieDataset data1 =dataset1;
            JFreeChart chart1 = ChartFactory.createPieChart3D("支出占比", data1, true, false, false);
            //设置百分比
            PiePlot pieplot1 = (PiePlot) chart1.getPlot();
            DecimalFormat df1 = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
            NumberFormat nf1 = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
            StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf1, df1);//获得StandardPieSectionLabelGenerator对象
            pieplot1.setLabelGenerator(sp1);//设置饼图显示百分比
            //没有数据的时候显示的内容
            pieplot1.setNoDataMessage("无数据显示");
            pieplot1.setCircular(false);
            pieplot1.setLabelGap(0.02D);
            pieplot1.setIgnoreNullValues(true);//设置不显示空值
            //设置不显示负值
            pieplot1.setIgnoreZeroValues(true);
            frame11 = new ChartPanel(chart1, true);
            chart1.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//设置标题字体
            PiePlot piePlot = (PiePlot) chart1.getPlot();//获取图表区域对象
            piePlot.setLabelFont(new Font("宋体", Font.BOLD, 10));//解决乱码
            chart1.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));
            DefaultPieDataset data = dataset;
            JFreeChart chart = ChartFactory.createPieChart3D("收入占比",data,true,false,false);
            //设置百分比
            PiePlot pieplot = (PiePlot) chart.getPlot();
            DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
            NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
            StandardPieSectionLabelGenerator sp = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//获得StandardPieSectionLabelGenerator对象
            pieplot.setLabelGenerator(sp);//设置饼图显示百分比
            //没有数据的时候显示的内容
            pieplot.setNoDataMessage("无数据显示");
            pieplot.setCircular(false);
            pieplot.setLabelGap(0.02D);
            pieplot.setIgnoreNullValues(true);//设置不显示空值
            pieplot.setIgnoreZeroValues(true);//设置不显示负值
            frame1=new ChartPanel (chart,true);
            chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
            PiePlot piePlot1= (PiePlot) chart.getPlot();//获取图表区域对象
            piePlot1.setLabelFont(new Font("宋体",Font.BOLD,10));//解决乱码
            chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,10));
            panel1.add(frame1);
            panel1.add(frame11);
            jf.add(panel1,BorderLayout.PAGE_START);
            area.setEditable(false);
            area1.setEditable(false);

        NumberFormat nf11 = NumberFormat.getPercentInstance();
        nf11.setMinimumFractionDigits(2);
        double zong=fuzhuang1+chifan1+jiaotong1+lijin1+zhufang1;
        double zong2=gupiao1+gongzi1;
            area.append("服装支出:"+nf11.format(fuzhuang1/zong)+" ");
            area.append("吃饭支出:"+nf11.format(chifan1/zong)+" ");
            area.append("交通支出:"+nf11.format(jiaotong1/zong)+" ");
            area.append("礼金支出:"+nf11.format(lijin1/zong)+" ");
            area.append("住房支出:"+nf11.format(zhufang1/zong));
            area1.append("股票收入:"+nf11.format(gupiao1/zong2)+" ");
            area1.append("工资收入:"+nf11.format(gongzi1/zong2));
            panel2.add(area1,BorderLayout.EAST);
            panel2.add(area,BorderLayout.WEST);
            jf.add(panel2,BorderLayout.PAGE_END);
    }
    }

