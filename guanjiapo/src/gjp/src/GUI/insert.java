package GUI;
import java .sql.*;
import java.util.Scanner;
public class insert {
    String wenben;
    String wenben1;
    String wenben2;
    int num;
    String[][] date =new String[20][4];//定义二维数组为数据
    public  void show()throws SQLException{
        for(int i=0;i<20;i++){
            if(date[i][0]!=null){
                num= Integer.parseInt(date[i][0]);
            }
            if(date[i][0]==null){
                break;
            }
        }
        AddSortController21 add=new AddSortController21();
        System.out.println(wenben);
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
            String insert = "INSERT INTO fenleiguanli(id,分类名称,父分类,说明) VALUES (?,?,?,?)";
            ps=conn.prepareStatement(insert);
            ps.setString(1, String.valueOf(num+1));//id号main函数中要进行++从7开始
            ps.setString(2,wenben);
            ps.setString(3,wenben1);
            ps.setString(4,wenben2);
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

