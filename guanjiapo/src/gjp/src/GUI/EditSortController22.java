package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditSortController22 {

    update update=new update();
    String wenben;
    String wenben1;
    String wenben2;
    String id=null;
    String[][] date =new String[20][4];//定义二维数组为数据
    public  void createAndShowGUI() {

        JFrame frame = new JFrame("编辑分类");
        frame.setLayout(null);
        frame.setSize(400,400);
        frame.setLocation(300,200);
        JLabel label = new JLabel("编辑分类" ,JLabel.CENTER);
        label.setFont(new Font("黑体",Font.PLAIN,18));
        label.setBounds(145, 20, 100, 20);
        JLabel label1 = new JLabel("父分类:");
        label1.setBounds(80, 60, 100, 25);
        JComboBox<String> comBox = new JComboBox<String> ();
        comBox.addItem("收入");
        comBox.addItem("支出");
        comBox.addActionListener(e->{
            wenben=(String)comBox.getSelectedItem();
            update.fufenlei=wenben;
        });
        comBox.setBounds(150, 60, 120, 25);
        JLabel label2 =new JLabel("分类名称: ");
        label2.setBounds(80, 100, 100, 30);
        JTextField textField = new JTextField(10);
        textField.setBounds(150, 100, 120, 30);
        JLabel label3 = new JLabel("分类说明:");
        label3.setBounds(80, 140, 120, 35);
        JTextArea showArea = new JTextArea(4,10);
        //创建一个滑动面板组件，将文本域作为其显示组件
        JScrollPane scrollPane = new JScrollPane(showArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        showArea.setEditable(true);//文本域可编辑
        scrollPane.setViewportView(showArea);
        scrollPane.setBounds(150, 145, 160, 90);
        JButton button =new JButton("取消");
        button.setBounds(85, 280, 80, 30);
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                frame.dispose();
                SortMngController2 sortMngController2=new SortMngController2();
                sortMngController2.createAndShowGUI();
            }
        };
        button.addActionListener(actionListener);
        JButton button2 = new JButton("确定");
        button2.setBounds(225, 280, 80, 30);
        ActionListener ok =new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update.date=date;
                wenben1=textField.getText();
                update.fenleimingcheng=wenben1;
                wenben2=showArea.getText();
                update.shuoming=wenben2;
                update.id=id;
                try {
                    update.show();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                frame.dispose();
            }
        };
        button2.addActionListener(ok);
        frame.add(label);
        frame.add(label1);
        frame.add(comBox);
        frame.add(label2);
        frame.add(textField);
        frame.add(label3);
        frame.add(scrollPane);
        frame.add(button);
        frame.add(button2);
        frame.setVisible(true);
    }
}