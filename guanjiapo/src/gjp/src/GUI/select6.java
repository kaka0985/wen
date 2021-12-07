package GUI;
import java .sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class select6 {
    String id = null;
    String shouzhi = null;
    String fenlei = null;
    String jiner = null;
    String zhanghu = null;
    String shijian = null;
    String shuoming = null;
    String[] date1=new String[20];
    String[][] date=new String[20][7];
    String wenben;
    String wenben1;
    String wenben2;
    String wenben3;
    int num=0;
    public  void show() throws SQLException, ParseException {

        Scanner scan = new Scanner(System.in);
        Statement stmt=null;
        Connection conn=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/管家婆";
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String select = "select *from zhangwuguanli";
            rs = stmt.executeQuery(select);//用于执行SQL中insert插入，update修改，delete删除啊语句，返回一个类型的值。
            int j=0;
            int m=0;
            while (rs.next()) {
                int i=0;
                id = rs.getString("id");
                date[j][i]=id;
                i++;
                shouzhi = rs.getString("收支");
                date[j][i]=shouzhi;
                String shouzhi1=shouzhi;
                i++;
                fenlei = rs.getString("分类");
                date[j][i]=fenlei;
                i++;
                jiner = rs.getString("金额");
                date[j][i]=jiner;
                i++;
                if(shouzhi1.equals("支出")){
                date1[m]=jiner;
                m++;
                }
                zhanghu = rs.getString("账户");
                date[j][i]=zhanghu;
                i++;
                shijian = rs.getString("创建时间");
                date[j][i]=shijian;
                i++;
                shuoming = rs.getString("说明");
                date[j][i]=shuoming;
                i++;
                j++;
                //在这监听器进行获取数据库信息
        }} catch (ClassNotFoundException e) {
            e.printStackTrace();
        } if (rs != null) {
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            conn = null;
        }
        for(int i=0;i<date1.length;i++){
            if(date1[i]!=null){
                    num++;
                }
                }
        for( int i=0;i<num-1;i++){
            for(int j=i;j<num-1;j++){
            int num1= Integer.parseInt(date1[i]);
            int num2= Integer.parseInt(date1[j+1]);
            String num3=null;
            if(num1>num2){
                num3=date1[i];
                date1[i]=date1[j+1];
                date1[j+1]=num3;
            }
        }
        }
        for(int i=0;i<num;i++){
            System.out.println(date1[i]);
        }
            }
        }






