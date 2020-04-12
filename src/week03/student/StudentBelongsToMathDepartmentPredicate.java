package week03.student;

public class StudentBelongsToMathDepartmentPredicate implements StudentPredicate {
    public boolean test(Student student) {
        return student.getDept_name().equals("Math");
    }
}
