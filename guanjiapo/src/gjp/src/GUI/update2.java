package GUI;
import javax.print.DocFlavor;
import javax.swing.table.TableRowSorter;
import java .sql.*;
import java.util.Scanner;
public class update2 {
    String id = null;
    String wenben;
    String wenben1;
    String wenben2;
    String wenben3;
    String wenben4;
    String wenben5;
    public  void show()throws SQLException{
        Scanner scan = new Scanner(System.in);
        Statement stmt=null;
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement ps=null;
        LedgerMngController1 ledgerMngController1=new LedgerMngController1();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/管家婆";
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String insert = "UPDATE  zhangwuguanli SET 收支=?,分类=?,金额=?,账户=?,创建时间=?,说明=? where id=?";
            ps=conn.prepareStatement(insert);
            ps.setString(1,wenben);
            ps.setString(2,wenben1);
            ps.setString(3,wenben2);
            ps.setString(4,wenben3);
            ps.setString(5,wenben4);
            ps.setString(6,wenben5);
            ps.setString(7, id);//id号main函数中要进行++从7开始
            ps.execute();
            ledgerMngController1.led();
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
