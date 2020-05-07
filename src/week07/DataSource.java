package week07;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DataSource {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://47.98.53.171/small_university?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "dxr";
    private static final String PASSWORD = "dxr";

    public static List<Student> getStudentList() {
        List<Student> studentList = new LinkedList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String selectQuery = "SELECT ID, name, dept_name, tot_cred FROM student";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while(resultSet.next()) {
                String ID = resultSet.getString("ID");
                String name = resultSet.getString("name");
                String departmentName = resultSet.getString("dept_name");
                int totalCredit = resultSet.getInt("tot_cred");
                Student student = new Student(ID, name, departmentName, totalCredit);
                studentList.add(student);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public static void main(String[] args) {
        List<Student> studentList = getStudentList();
        studentList.forEach(System.out::println);
    }
}
