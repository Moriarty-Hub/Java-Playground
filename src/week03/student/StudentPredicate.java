package week03.student;

@FunctionalInterface
public interface StudentPredicate {
    boolean test(Student student);
}
