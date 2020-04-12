package week03.student;

public class StudentBelongsToHistoryDepartmentPredicate implements StudentPredicate {
    public boolean test(Student student) {
        return student.getDept_name().equals("History");
    }
}
