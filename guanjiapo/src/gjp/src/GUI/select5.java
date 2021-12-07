package GUI;
import java .sql.*;
import java.util.Scanner;
public class select5 {
    String id = null;
    String shouzhi = null;
    String fenlei = null;
    String jiner = null;
    String zhanghu = null;
    String shijian = null;
    String shuoming = null;
    String wenben2;
    String wenben3;
    String[] num1= new String[20];
    String[][] date =new String[20][7];//定义二维数组为数据
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
                id = rs.getString("id");
                shouzhi = rs.getString("收支");
                fenlei = rs.getString("分类");
                jiner = rs.getString("金额");
                zhanghu = rs.getString("账户");
                shijian = rs.getString("创建时间");
                shuoming = rs.getString("说明");
                for(int m=0;m<num1.length;m++){
                    int n=0;
                    if(num1[m]!=null){
                    if(num1[m].equals(id)){
                        if(wenben2.equals(shouzhi)){
                            if(wenben3.equals(fenlei)){
                                n=1;
                            }
                        }
                    }
                }
                if(n==1){
                    int i=0;
                date[j][i]=id;
                System.out.print(date[j][i]+" " );
                i++;
                date[j][i]=shouzhi;
                    System.out.print(date[j][i]+" " );
                i++;
                date[j][i]=fenlei;
                    System.out.print(date[j][i]+" " );
                i++;
                date[j][i]=jiner;
                    System.out.print(date[j][i]+" " );
                i++;
                date[j][i]=zhanghu;
                    System.out.print(date[j][i]+" " );
                i++;
                date[j][i]=shijian;
                    System.out.print(date[j][i]+" " );
                i++;
                date[j][i]=shuoming;
                    System.out.print(date[j][i]+" " );
                i++;
                j++;}}
                //在这监听器进行获取数据库信息
        } }catch (ClassNotFoundException e) {
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
