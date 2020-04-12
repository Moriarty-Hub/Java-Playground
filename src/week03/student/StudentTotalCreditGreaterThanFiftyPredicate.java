package week03.student;

public class StudentTotalCreditGreaterThanFiftyPredicate implements StudentPredicate {
    public boolean test(Student student) {
        return student.getTot_cred() > 50;
    }
}
