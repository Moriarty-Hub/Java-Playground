package week07;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Main {

    public static void main(String[] args) {

        List<Student> studentList = DataSource.getStudentList();

        // Print the maximum total credit
        Comparator<Student> totalCreditComparator = Comparator.comparingInt(Student::getTotalCredit);
        Optional<Student> maxTotalCredit = studentList.stream().collect(maxBy(totalCreditComparator));
        maxTotalCredit.ifPresent(student -> System.out.println("Maximum total credit: " + student.getTotalCredit()));

        // Print the sum of total credit
        int sumOfTotalCredit = studentList.stream().collect(summingInt(Student::getTotalCredit));
        System.out.println("Sum of total credit: " + sumOfTotalCredit);

        // Print the statistics of total credit
        IntSummaryStatistics totalCreditStatistics = studentList.stream().collect(summarizingInt(Student::getTotalCredit));
        System.out.println("Total credit statistics: " + totalCreditStatistics.toString());

        // Print all students' name, split by comma
        String students = studentList.stream().map(Student::getName).collect(joining(", "));
        System.out.println("Student name list: " + students);

        // Print all students, grouped by department
        Map<String, List<Student>> studentsByDepartment = studentList.stream().collect(groupingBy(Student::getDepartmentName));
        System.out.println(studentsByDepartment);

        // Print all students, partitioned by their total credit greater than 100 or not
        Map<Boolean, List<Student>> studentPartitionByTotalCredit = studentList.stream().collect(partitioningBy(student -> student.getTotalCredit() > 100));
        System.out.println(studentPartitionByTotalCredit);
    }
}
