package org.kdu.JavaHandOn1;

public class Student
{
    private int id;
    private String name;
    private int age;
    private String grade;

    Student(int id,String name,int age,String grade){
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGrade() {
        return grade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void printStudent()
    {
        System.out.print("STUDENT DETAILS : ");
        System.out.println("ID : " + id);
        System.out.println("NAME : "+ name);
        System.out.println("AGE : " + age);
        System.out.println("GRADE : " + grade);
    }
}
