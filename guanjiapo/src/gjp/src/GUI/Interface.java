package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface {

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("欢迎使用管家婆家庭记账软件");
        frame.setLayout(new BorderLayout());
        frame.setSize(600,400);
        frame.setLocation(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon("D:\\idea\\guanjiapo\\src\\gjp\\src\\GUI\\guanjiapo.png");
        Image img =icon.getImage();
        img = img.getScaledInstance(600, 320, Image.SCALE_DEFAULT);
        icon.setImage(img);
        label.setIcon(icon);
        JPanel panel = new JPanel();
        JButton button1 = new JButton("财务管理");
        JButton button2 = new JButton("分类管理");


        ActionListener sort =new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//财务管理
                LedgerMngController1 led =new LedgerMngController1();
                led.led();

            }
        };
        ActionListener mon =new ActionListener() {//分类管理
            @Override
            public void actionPerformed(ActionEvent e) {
                SortMngController2 sort =new SortMngController2();
                sort.createAndShowGUI();

            }
        };
        button1.addActionListener(sort);
        button2.addActionListener(mon);



        panel.add(button1);
        panel.add(button2);
        frame.add(label,BorderLayout.PAGE_START);
        frame.add(panel,BorderLayout.PAGE_END);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}