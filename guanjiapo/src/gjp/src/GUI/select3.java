package GUI;
import java .sql.*;
import java.util.Scanner;
public class select3 {
    String id = null;
    String shouzhi = null;
    String fenlei = null;
    String jiner = null;
    String zhanghu = null;
    String shijian = null;
    String shuoming = null;
    String[][] date =new String[20][20];//定义二维数组为数据
    test test=new test();
    public  void show()throws SQLException{
        SortMngController2 sor= new SortMngController2();
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
            System.out.println("id  |  收支  |  分类  |   金额  |   账户" + "|  创建时间   | 说明  ");
            int j=0;
            while (rs.next()) {
                int i=0;
                id = rs.getString("id");
                date[j][i]=id;
                test.date[j][i]=id;
                i++;
                shouzhi = rs.getString("收支");
                date[j][i]=shouzhi;
                test.date[j][i]=shouzhi;
                i++;
                fenlei = rs.getString("分类");
                date[j][i]=fenlei;
                test.date[j][i]=fenlei;
                i++;
                jiner = rs.getString("金额");
                date[j][i]=jiner;
                test.date[j][i]=jiner;
                i++;
                zhanghu = rs.getString("账户");
                date[j][i]=zhanghu;
                test.date[j][i]=zhanghu;
                i++;
                shijian = rs.getString("创建时间");
                date[j][i]=shijian;
                test.date[j][i]=shijian;
                i++;
                shuoming = rs.getString("说明");
                date[j][i]=shuoming;
                test.date[j][i]=shuoming;
                i++;
                j++;
                //在这监听器进行获取数据库信息
                System.out.println(id + "    |    " + shouzhi + "|    " + fenlei + "|   " + jiner + "|   " +zhanghu+ "|   " +shijian+ "|   " +shuoming);
            }
            test.show();
            test.show2();
        } catch (ClassNotFoundException e) {
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

    }
}
