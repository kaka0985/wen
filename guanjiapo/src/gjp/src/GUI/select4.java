package GUI;
import java .sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class select4 {
    String id = null;
    String shouzhi = null;
    String fenlei = null;
    String jiner = null;
    String zhanghu = null;
    String shijian = null;
    String shuoming = null;
    String[] date1=new String[20];
    String[] num=new String[20];
    String wenben;
    String wenben1;
    String wenben2;
    String wenben3;
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
            while (rs.next()) {
                id = rs.getString("id");
                shouzhi = rs.getString("收支");
                fenlei = rs.getString("分类");
                jiner = rs.getString("金额");
                zhanghu = rs.getString("账户");
                shijian = rs.getString("创建时间");
                date1[j]=shijian;
                shuoming = rs.getString("说明");
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
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        int j=0;
        Date date=sdf.parse(wenben);
        Date date2=sdf.parse(wenben1);
        for(int i=0;i<date1.length;i++){
            if(date1[i]!=null){
                Date date3=sdf.parse(date1[i]);
                if(date.getTime()<date3.getTime()&&date3.getTime()<date2.getTime()){
                    num[j]= String.valueOf(i+1);
                    j++;
                }
            }
        }

    }

//    public void show2() throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        int j=0;
//            Date date=sdf.parse(wenben);
//            Date date2=sdf.parse(wenben1);
//            for(int i=0;i<date1.length;i++){
//                    if(date1[i]!=null){
//                        Date date3=sdf.parse(date1[i]);
//                        if(date.getTime()<date3.getTime()&&date3.getTime()<date2.getTime()){
//                            num[j]= String.valueOf(i)+1;
//                            j++;
//                        }
//                    }
//            }
//
//        }
    }

