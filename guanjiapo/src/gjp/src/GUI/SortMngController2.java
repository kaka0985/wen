package GUI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class SortMngController2 {
    int num=0;
    select select=new select();
    delect delect=new delect();
    update update=new update();
    String[][] date =new String[20][4];//定义二维数组为数据
    String[] date1=new String[20];
    public  void createAndShowGUI() {
        JFrame frame = new JFrame("分类管理");
        frame.setLayout(new FlowLayout());
        frame.setSize(600,400);
        frame.setLocation(300,200);
        JLabel label = new JLabel("分类管理" ,JLabel.CENTER);
        label.setFont(new Font("黑体",Font.PLAIN,20));
        try {
            select.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        date=select.date;
        Vector columnNames = new Vector(); //设置列名

        columnNames.add("ID");
        columnNames.add("分类说明");
        columnNames.add("父分类");
        columnNames.add("说明");
        Vector rowData = new Vector();
//        Vector hang = new Vector();//设置每一行的值
        for(int i=0;i<20;i++){
            String a=null;
            Vector hang = new Vector();
            for(int j=0;j<4;j++){
                if(date[i][j]!=null){
                    a=date[i][j];
                    hang.add(a);
                }
            }rowData.add(hang);
        }
         DefaultTableModel d = new DefaultTableModel(rowData, columnNames);//新建数据模型
        JTable table = new JTable(d);//声明table使用d数据模型
        table.setPreferredScrollableViewportSize(new Dimension(500,230));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel panel = new JPanel();
        JButton button = new JButton("添加");
        JButton button1 = new JButton("编辑");
        JButton button2 = new JButton("删除");
        JButton button3 = new JButton("关闭");
        panel.add(button);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        ActionListener close = new ActionListener() {//关闭
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                frame.dispose();
            }
        };
        ActionListener add =new ActionListener() {//添加
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSortController21 addSortController = new AddSortController21();
                select select11=new select();
                try {
                    select11.show();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                addSortController.date= select11.date;
                addSortController.createAndShowGUI();
                frame.dispose();
            }
        };
        ActionListener bj =new ActionListener() {//编辑
            @Override
            public void actionPerformed(ActionEvent e) {
                int count=table.getSelectedRow();//鼠标点击获得id好
                EditSortController22 editSortController = new EditSortController22();
                editSortController.date=select.date;
                editSortController.id= String.valueOf((count));
                editSortController.createAndShowGUI();
                frame.dispose();
            }
        };
        ActionListener delect1 =new ActionListener() {//删除
            @Override
            public void actionPerformed(ActionEvent e) {
                int count=table.getSelectedRow();
                delect.num= String.valueOf((count+1));
                delect.date=select.date;
                select select11=new select();
                try {
                    delect.show();
                    select11.show();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                date=select11.date;
                d.getDataVector().clear();
                for(int i=0;i<20;i++){
                    String a;
                    Vector hang1 = new Vector();
                    for(int j=0;j<4;j++){
                        if(date[i][j]!=null){
                            System.out.println(date[i][j]);
                            a=date[i][j];
                            hang1.add(a);
                        }
                    }rowData.add(hang1);
                }
                DefaultTableModel d = new DefaultTableModel(rowData, columnNames);
                table.updateUI();
            }
        };
        button.addActionListener(add);
        button1.addActionListener(bj);
        button2.addActionListener(delect1);
        button3.addActionListener(close);
        frame.add(label,BorderLayout.PAGE_START);
        frame.add(scrollPane,BorderLayout.CENTER);
        //frame.add(new JScrollPane(table),BorderLayout.CENTER);
        //frame.add(panel);
        frame.add(panel,BorderLayout.PAGE_END);
        frame.setVisible(true);
    }
}