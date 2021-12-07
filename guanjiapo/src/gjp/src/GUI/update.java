package GUI;
import javax.print.DocFlavor;
import javax.swing.table.TableRowSorter;
import java .sql.*;
import java.util.Scanner;
public class update {
    String shuoming=null;
    String  fufenlei=null;
    String fenleimingcheng=null;
    String  id=null;
    String[][] date =new String[20][4];//定义二维数组为数据
    public  void show()throws SQLException{
        String id1=date[Integer.parseInt(id)][0];
        Scanner scan = new Scanner(System.in);
        Statement stmt=null;
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement ps=null;
        SortMngController2 sortMngController2=new SortMngController2();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/管家婆";
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String n="我修改成功啦";
            String insert = "UPDATE  fenleiguanli SET 分类名称=?,父分类=?,说明=? where id=?";
            ps=conn.prepareStatement(insert);
            ps.setString(1,fenleimingcheng);
            ps.setString(2,fufenlei);
            ps.setString(3,shuoming);
            ps.setString(4,id1);
            ps.execute();
            sortMngController2.createAndShowGUI();
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
