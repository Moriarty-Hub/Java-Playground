package week03.student;

public class Student {
    private Integer id;
    private String name;
    private String dept_name;
    private Integer tot_cred;

    public Student(Integer id, String name, String dept_name, Integer tot_cred) {
        this.id = id;
        this.name = name;
        this.dept_name = dept_name;
        this.tot_cred = tot_cred;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public Integer getTot_cred() {
        return tot_cred;
    }

    public void setTot_cred(Integer tot_cred) {
        this.tot_cred = tot_cred;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Department name: " + dept_name + ", Total credit: " + tot_cred;
    }
}
