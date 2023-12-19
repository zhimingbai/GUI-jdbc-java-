import java.sql.*;
import java.util.ArrayList;

public class dataBase {


    //插入
    public static void intoData(String id, String name, String clas) {
        try {
            Connection connection = DriverManager.getConnection(Util.url,Util.username, Util.password);
            String sql1 = "INSERT INTO ruangong_2201.sys_student (id,name,class) VALUES (?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql1);

            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, clas);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("插入成功！");
            }

            pstmt.close();
            connection.close();

        } catch (SQLException e) {throw new RuntimeException(e);}
    }


    //删除
    public static boolean deleteData(String id)  throws Exception{
        boolean state=false;

            Connection connection = DriverManager.getConnection(Util.url,Util.username, Util.password);
            String sql1 = "DELETE FROM ruangong_2201.sys_student WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql1);

            pstmt.setString(1, id);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                state=true;
                System.out.println("删除成功！");
            }


            pstmt.close();
            connection.close();
        return state;
    }


    //查询
    public static Student queryData(String id) throws Exception{
        Student student;


            Connection connection = DriverManager.getConnection(Util.url, Util.username, Util.password);
            String sql = "SELECT * FROM ruangong_2201.sys_student WHERE id= ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, id);

            ResultSet resultSet = pstmt.executeQuery();
            student = new Student();

            while (resultSet.next()) {
                student.setId(resultSet.getString("id"));
                student.setName(resultSet.getString("name"));
                student.setClas(resultSet.getString("class"));
            }
            resultSet.close();
            pstmt.close();
            connection.close();

        return student;
    }


    //加载数据（放入list中）
    public static void loadData(ArrayList<Student> list) throws Exception{


        Student student;
            Connection connection = DriverManager.getConnection(Util.url, Util.username, Util.password);
            String sql = "SELECT * FROM ruangong_2201.sys_student";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            ResultSet resultSet = pstmt.executeQuery();


            while (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getString(1));
                student.setName(resultSet.getString(2));
                student.setClas(resultSet.getString(3));
                list.add(student);
            }
            resultSet.close();
            pstmt.close();
            connection.close();

    }


    //清空所有的数据
    public static void clearData() throws Exception{

        Connection connection = DriverManager.getConnection(Util.url, Util.username, Util.password);
        String sql = "delete from ruangong_2201.sys_student";

        Statement statement = connection.createStatement();
        System.out.println("12312312");

        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

}
