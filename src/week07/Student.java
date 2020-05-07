package week07;

public class Student {
    private String id;
    private String name;
    private String departmentName;
    private int totalCredit;

    public Student(String id, String name, String departmentName, int totalCredit) {
        this.id = id;
        this.name = name;
        this.departmentName = departmentName;
        this.totalCredit = totalCredit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Department: " + departmentName + ", Total Credit: " + totalCredit;
    }
}
