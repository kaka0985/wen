package GUI;
import java .sql.*;
import java.util.Scanner;
public class select {
    String id = null;
    String name = null;
    String father = null;
    String explain = null;
    String[][] date =new String[20][4];//定义二维数组为数据
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
            String select = "select *from fenleiguanli";
            rs = stmt.executeQuery(select);//用于执行SQL中insert插入，update修改，delete删除啊语句，返回一个类型的值。
            System.out.println("id  |  父类名称  | 父分类" + "|  说明  ");
            int j=0;
            while (rs.next()) {
                int i=0;
                id = rs.getString("id");
                date[j][i]= id;
                i++;
                name = rs.getString("分类名称");
                date[j][i]=name;
                i++;
                father = rs.getString("父分类");
                date[j][i]=father;
                i++;
                explain = rs.getString("说明");
                date[j][i]=explain;
                i++;
                j++;
                //在这监听器进行获取数据库信息
                System.out.println(id + "    |    " + name + "|    " + father + "|   " + explain );
                                }
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
