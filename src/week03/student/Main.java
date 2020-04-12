package week03.student;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static List<Student> getAllStudentFromDatabase() {
        final String URL = "jdbc:mysql://47.98.53.171:3306/university";
        final String USERNAME = "dxr";
        final String PASSWORD = "dxr";

        List<Student> students = new LinkedList<>();
        String sql = "SELECT ID, name, dept_name, tot_cred FROM student";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            // 'com.mysql.jdbc.Driver' is deprecated
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString(1));
                String name = resultSet.getString(2);
                String dept_name = resultSet.getString(3);
                int tot_cred = resultSet.getInt(4);
                Student student = new Student(id, name, dept_name, tot_cred);
                students.add(student);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static List<Student> filterStudent(List<Student> students, StudentPredicate studentPredicate) {
        List<Student> result = new LinkedList<>();
        for (Student student : students) {
            if (studentPredicate.test(student)) {
                result.add(student);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Student> students = getAllStudentFromDatabase();

        List<Student> studentsBelongToHistoryDepartment = filterStudent(students, new StudentBelongsToHistoryDepartmentPredicate());
        System.out.println("All students belong to history department");
        for(Student student : studentsBelongToHistoryDepartment) {
            System.out.println(student.toString());
        }
        System.out.println("------------------------------------------------");

        List<Student> studentsBelongToMathDepartment = filterStudent(students, new StudentBelongsToMathDepartmentPredicate());
        System.out.println("All students belong to math department");
        for(Student student : studentsBelongToMathDepartment) {
            System.out.println(student.toString());
        }
        System.out.println("------------------------------------------------");

        List<Student> studentTotalCreditGreaterThanFifty = filterStudent(students, new StudentTotalCreditGreaterThanFiftyPredicate());
        System.out.println("All students total credit greater than fifty");
        for(Student student : studentTotalCreditGreaterThanFifty) {
            System.out.println(student.toString());
        }
    }
}
