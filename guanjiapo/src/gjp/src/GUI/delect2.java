package GUI;
import javax.print.DocFlavor;
import java .sql.*;
import java.util.Scanner;
public class delect2 {
    String id;
    public  void show()throws SQLException{
        Scanner scan = new Scanner(System.in);
        Statement stmt=null;
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement ps=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/管家婆";
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String insert = "DELETE from zhangwuguanli where id=?";
            ps=conn.prepareStatement(insert);
            ps.setString(1,id);
            System.out.println(id);
            ps.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (ps != null) {
            try {
                ps.close();
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