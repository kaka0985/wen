package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;


//财务管理


public class LedgerMngController1 {
    select2 select2=new select2();
    select3 select3=new select3();
    select4 select4=new select4();
    select5 select5=new select5();
    select6 select6=new select6();
    String[][] date=new String[10][7];
    String[] date1=new String[20];
    int num=0;
    String[] num1=new String[20];
    String wenben;
    String wenben1;
    String wenben2;
    String wenben3;
    public   void led(){
         JFrame f =new JFrame("账务管理：");
         f.setLayout(new FlowLayout());
         f.setSize(650,360);
         f.setLocation(200,200);
//         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         f.setVisible(true);
         JPanel p1 =new JPanel();
         JPanel p2 =new JPanel();
         JPanel p3 =new JPanel();
         JLabel label =new JLabel("账务管理");
         label.setFont(new Font("黑体",Font.PLAIN,20));
         JLabel st =new JLabel("起始：");
         JLabel con =new JLabel("至：");
         JLabel sz =new JLabel("收/支");
         JLabel so =new JLabel("分类：");
         JButton se =new JButton("查询");
         JButton ad =new JButton("添加");
         JButton ed =new JButton("编辑");
         JButton del =new JButton("删除");
         JButton to =new JButton("收/支比重统计");
         JButton px=new JButton("支出金额排序");
         JButton cl =new JButton("关闭");
         JTextField shouru =new JTextField("总收入：",9);
         JTextField zhichu =new JTextField("总支出：",9);
         String[] title ={"ID","收/支","分类","金额","账户","创建时间","说明"};
         try {
             select2.show();
             date=select2.date;
         } catch (SQLException e) {
             e.printStackTrace();
         }
        Vector columnNames = new Vector(); //设置列名

        columnNames.add("ID");
        columnNames.add("收/支");
        columnNames.add("分类");
        columnNames.add("金额");
        columnNames.add("账户");
        columnNames.add("创建时间");
        columnNames.add("说明");

        Vector rowData = new Vector();

//        Vector hang = new Vector();//设置每一行的值

        for(int i=0;i<10;i++){
            String a=null;
            Vector hang = new Vector();
            for(int j=0;j<7;j++){
                if(date[i][j]!=null){
                    a=date[i][j];
                    hang.add(a);
            }
            }rowData.add(hang);
        }
         DefaultTableModel d = new DefaultTableModel(rowData, columnNames);//新建数据模型
         JTable table = new JTable(d);
         JScrollPane scrollPane = new JScrollPane();
         scrollPane.setViewportView(table);
         scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
         table.setPreferredScrollableViewportSize(new Dimension(590, 130));
         JTextField textst =new JTextField("",10);//起始时间
         JTextField textcon =new JTextField("",10);//终止时间
        JComboBox<String> sz1=new JComboBox<>();
        sz1.addItem("-请选择-");
        sz1.addItem("收入");
        sz1.addItem("支出");
        JComboBox<String> com=new JComboBox<>();
        com.addItem("-请选择-");
        com.addItem("=请选择=");
        com.addItem("服装支出");
        com.addItem("吃饭支出");
        com.addItem("交通支出");
        com.addItem("住房支出");
        com.addItem("礼金支出");
        com.addItem("工资收入");
        com.addItem("股票收入");
        sz1.addActionListener(e->{
        });
        com.addActionListener(e->{
        });

         ActionListener close=new ActionListener() {//关闭
             @Override
             public void actionPerformed(ActionEvent e) {
                 f.dispose();
             }
         };
        ActionListener px1=new ActionListener() {//排序
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    select6.show();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                date=select6.date;
                date1=select6.date1;
                d.getDataVector().clear();
                Vector columnNames = new Vector(); //设置列名
                columnNames.add("ID");
                columnNames.add("收/支");
                columnNames.add("分类");
                columnNames.add("金额");
                columnNames.add("账户");
                columnNames.add("创建时间");
                columnNames.add("说明");
                Vector rowData = new Vector();
                int num=0;
                int num2=select6.num;
                for(int i=0;i<date.length;i++){
                    if(date[i][1]!=null){
                        num++;
                    }
                }
                for(int i=0;i<num2;i++){
                    String a=null;
                    int m1=0;
                    int m=0;
                    for(;m<num;m++){
                        if(date1[i].equals(date[m][3])){
                            Vector hang = new Vector();
                            for(int j=0;j<7;j++){
                                a=date[m][j];
                                hang.add(a);
                            }rowData.add(hang);
                        }
                    }
                }
                d.setDataVector(rowData, columnNames);
                table.updateUI();
            }
        };
        px.addActionListener(px1);
         cl.addActionListener(close);
         ActionListener add =new ActionListener() {//添加
             @Override
             public void actionPerformed(ActionEvent e) {
                 num= select2.num;
                AddLedgerController11 addLedgerController=new AddLedgerController11();
                addLedgerController.num=num;
                addLedgerController.AddLedgerController();
                 f.dispose();
             }
         };
         ad.addActionListener(add);
         ActionListener bj=new ActionListener() {//编辑
             @Override
             public void actionPerformed(ActionEvent e) {
                 int count= table.getSelectedRow();
             EditLederController12 editLederController=new EditLederController12();
             editLederController.id= String.valueOf(count+1);
             editLederController.EditLederController();
                 f.dispose();
             }
         };
         ed.addActionListener(bj);
         ActionListener shouzhi =new ActionListener() {//百分比
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                     select3.show();
                 } catch (SQLException throwables) {
                     throwables.printStackTrace();
                 }
             }
         };
       to.addActionListener(shouzhi);
        ActionListener delect=new ActionListener() {//删除
            @Override
            public void actionPerformed(ActionEvent e) {
                int count=0;
                        count=table.getSelectedRow();
                        System.out.println(count);
                select2 select33=new select2();
                try {
                    delect2 delect2=new delect2();
                    delect2.id= String.valueOf(count+1);
                    delect2.show();
                    select33.show();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                date=select33.date;
                d.getDataVector().clear();
                Vector columnNames = new Vector(); //设置列名
                columnNames.add("ID");
                columnNames.add("收/支");
                columnNames.add("分类");
                columnNames.add("金额");
                columnNames.add("账户");
                columnNames.add("创建时间");
                columnNames.add("说明");
                Vector rowData = new Vector();
                for(int i=0;i<10;i++){
                    String a=null;
                    Vector hang = new Vector();
                    for(int j=0;j<7;j++){
                        if(date[i][j]!=null){
                            a=date[i][j];
                            hang.add(a);
                        }
                    }rowData.add(hang);
                }
                d.setDataVector(rowData, columnNames);
                table.updateUI();
            }
        };
        ActionListener select111=new ActionListener() {//查询
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    wenben=textst.getText();
                    wenben1=textcon.getText();
                    wenben2= (String) sz1.getSelectedItem();
                    wenben3= (String) com.getSelectedItem();
                    select4.wenben=wenben;
                    select4.wenben1=wenben1;
                    select4.show();
                    num1=select4.num;
                } catch (SQLException | ParseException x) {
                    x.printStackTrace();
                }
                try {
                    select5.wenben2=wenben2;
                    select5.wenben3=wenben3;
                    select5.num1=num1;
                    select5.show();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                date=select5.date;
                d.getDataVector().clear();
                Vector columnNames = new Vector(); //设置列名
                columnNames.add("ID");
                columnNames.add("收/支");
                columnNames.add("分类");
                columnNames.add("金额");
                columnNames.add("账户");
                columnNames.add("创建时间");
                columnNames.add("说明");
                Vector rowData = new Vector();
                for(int i=0;i<10;i++){
                    String a=null;
                    Vector hang = new Vector();
                    for(int j=0;j<7;j++){
                        if(date[i][j]!=null){
                            a=date[i][j];
                            hang.add(a);
                        }
                    }rowData.add(hang);
                }
                d.setDataVector(rowData, columnNames);
                table.updateUI();
            }

        };
        se.addActionListener(select111);
        del.addActionListener(delect);
         p1.add(label);
         p2.add(st);
         p2.add(textst);
         p2.add(con);
         p2.add(textcon);
         p2.add(sz);
         p2.add(sz1);
         p2.add(so);
         p2.add(com);
         p2.add(se);
         p3.add(ad);
         p3.add(ed);
         p3.add(del);
         p3.add(px);
         p3.add(to);
         p3.add(cl);
         scrollPane.add(shouru);
         scrollPane.add(zhichu);
         f.add(p1,BorderLayout.PAGE_START);
         f.add(p2);
         f.add(scrollPane,BorderLayout.CENTER);
         f.add(p3,BorderLayout.PAGE_END);
         f.setVisible(true);
     }
        public void bing(){
        }
}
