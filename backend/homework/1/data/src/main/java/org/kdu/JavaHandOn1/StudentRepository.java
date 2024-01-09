package org.kdu.JavaHandOn1;

import java.util.ArrayList;
import java.util.Objects;

public class StudentRepository {
    private static final ArrayList<Student> StudentList = new ArrayList<>();
    loggingSystem ls = new loggingSystem();

    /**
     * @param id INTEGER --> unique student id
     * @return True --> if id is already present
     *          False --> if id is not present
     */
    private boolean checkIdAlreadyPresent(int id)
    {
        for (Student current_student : StudentList) {
            if (current_student.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param std Student --> student details
     */
    public void add(Student std)
    {
        if(checkIdAlreadyPresent(std.getId()))
        {
            System.out.println(std.getId() + " is  already present please enter different id");
            ls.logWarn("Addition denied due to similar id present");
            return;
        }
        StudentList.add(std);
        System.out.println("Student Added Successfully");
        ls.logInfo(" Student with " + std.getId() + " Added Successfully");
    }

    /**
     * @param id : INTEGER --> unique student id
     * @return Student --> returns student details
     */
    public Student retrieve(int id)
    {
        
        for (Student current_student : StudentList) {
            if (current_student.getId() == id) {
                ls.logInfo("student with id : " + id + " Information retrieved");
                return current_student;
            }
        }
        ls.logWarn("There is no student with " + id + " Please Enter valid id");
        System.out.println("ID NOT PRESENT PLEASE TRY GIVE VALID ID");
        return new Student(0,"NA",0,"NA");
    }

    /**
     * @param name String --> student name
     * @return LIST OF STUDENT WITH THAT NAME
     */
    public ArrayList<Student> retrieve(String name)
    {
        ArrayList<Student> result = new ArrayList<>();
        for (Student current_student : StudentList) {
            if (Objects.equals(current_student.getName(), name)) {
                result.add(current_student);
            }
        }
        ls.logInfo("retrieved information about students with name : " + name);
        return result;
    }

    /**
     * @param id INTEGER --> unique student id
     * @param name LIST OF STUDENT WITH THAT NAME
     * @return Student --> returns student details
     */
    public Student retrieve(int id,String name)
    {
        for (Student current_student : StudentList) {
            if (current_student.getId() == id && Objects.equals(current_student.getName(), name)) {
                ls.logInfo("student with id : " + id + " Information retrieved");
                return current_student;
            }
        }
        ls.logWarn("There is no student with id : " + id + " and name : " + name);
        System.out.println("ID & NAME NOT PRESENT PLEASE TRY GIVE VALID ID");
        return new Student(0,"NA",0,"NA");
    }


    public Student update(int id,String name,int age,String grade)
    {
        Student current_student = this.retrieve(id);
        // we can't change or update id because we are taking it as unique
        // and if change it will have clash so avoiding it for now
        current_student.setName(name);
        current_student.setAge(age);
        current_student.setGrade(grade);
        ls.logInfo(" Student with " + current_student.getId() + " Updated the name, age and grade Successfully");
        System.out.println("Student Added Successfully");
        return current_student;
    }

    public Student update(int id,String name)
    {
        Student current_student = this.retrieve(id);
        // we can't change or update id because we are taking it as unique and
        // if change it will have clash so avoiding it for now
        current_student.setName(name);
        ls.logInfo(" Student with " + current_student.getId() + " Updated the name Successfully");
        return current_student;
    }

    public Student update(int id,int age)
    {
        Student current_student = this.retrieve(id);
        current_student.setAge(age);
        ls.logInfo(" Student with " + current_student.getId() + " Updated the age Successfully");
        return current_student;
    }

}
