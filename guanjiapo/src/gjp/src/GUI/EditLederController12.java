package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditLederController12 {
    String wenben;
    String wenben1;
    String wenben2;
    String wenben3;
    String wenben4;
    String wenben5;
    String id;
    public  void EditLederController(){
        update2 update2=new update2();
        JFrame f=new JFrame();
        f.setLayout(null);
        f.setSize(470,550);
        f.setLocation(200,200);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel text = new JLabel("编辑账务");
        text.setFont(new Font("黑体",Font.PLAIN,20));
        JLabel so =new JLabel("分类：");
        JLabel sz =new JLabel("收/支");
        JLabel user =new JLabel("账户");
        JLabel mon  =new JLabel("金额");
        JLabel time =new JLabel("时间");
        JLabel sm   =new JLabel("说明");
        JTextField usertext =new JTextField(15);
        JTextField montext =new JTextField(7);
        JTextField timetext =new JTextField(10);
        JComboBox<String> sz1=new JComboBox<>();
        sz1.addItem("-请选择-");
        sz1.addItem("收入");
        sz1.addItem("支出");
        JComboBox<String> com=new JComboBox<>();
        com.addItem("=请选择=");
        com.addItem("服装支出");
        com.addItem("吃饭支出");
        com.addItem("交通支出");
        com.addItem("住房支出");
        com.addItem("礼金支出");
        com.addItem("工资收入");
        com.addItem("股票收入");
        sz1.addActionListener(e->{
            wenben=(String)sz1.getSelectedItem();
            update2.wenben=wenben;
        });
        com.addActionListener(e->{
            wenben1=(String)com.getSelectedItem();
            update2.wenben1=wenben1;
        });
        JPanel p2 =new JPanel();
        JTextArea Area = new JTextArea(10,20);
        JScrollPane scroll =new JScrollPane(Area);
        Area.setEnabled(true);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        JPanel p3 =new JPanel();
        JButton cl =new JButton("关闭");
        JButton ok =new JButton("确定");

        ActionListener close =new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                LedgerMngController1 ledgerMngController1=new LedgerMngController1();
                ledgerMngController1.led();
            }
        };
        ActionListener queding =new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wenben4=timetext.getText();
                wenben5=Area.getText();
                wenben2=montext.getText();
                wenben3=usertext.getText();
                update2.wenben=wenben;
                update2.wenben1=wenben1;
                update2.wenben2=wenben2;
                update2.wenben3=wenben3;
                update2.wenben4=wenben4;
                update2.wenben5=wenben5;
                update2.id=id;
                f.dispose();
                try {
                    update2.show();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        };
         cl.addActionListener(close);
         ok.addActionListener(queding);


        text.setBounds(180,20,100,20);
        f.add(text);
        sz.setBounds(40,60,100,15);
        f.add(sz);
        sz1.setBounds(90,60,100,20);
        f.add(sz1);
        so.setBounds(40,100,100,15);
        f.add(so);
        com.setBounds(90,100,100,20);
        f.add(com);
        user.setBounds(40,140,100,15);
        f.add(user);
        usertext.setBounds(90,140,250,20);
        f.add(usertext);
        mon.setBounds(40,180,100,15);
        f.add(mon);
        montext.setBounds(90,180,100,20);
        f.add(montext);
        time.setBounds(40,220,100,15);
        f.add(time);
        timetext.setBounds(90,220,80,20);
        f.add(timetext);
        sm.setBounds(40,260,100,15);
        f.add(sm);
        scroll.setBounds(90,260,250,150);
        f.add(scroll);
        cl.setBounds(30,440,90,22);
        f.add(cl);
        ok.setBounds(320,440,90,22);
        f.add(ok);


        f.setVisible(true);
    }

}
