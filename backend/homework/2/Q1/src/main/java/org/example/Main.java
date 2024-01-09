package org.example;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LoggingSystem ls = new LoggingSystem();

        int[] studentIds =  {1001, 1002};
        char[][] studentGrades = { { 'A', 'A', 'A', 'B' }, { 'A', 'B', 'B' } } ;

        double[] gpaList = StudentUtil.calculateGPA(studentIds,studentGrades);
        ls.logInfo("Gpa List : ");
        for (double v : gpaList) {
           ls.logInfo( v + " ");
        }

        double lower = 3.2;
        double higher = 3.5;
        int[] students = StudentUtil.getStudentsByGPA(lower,higher,studentIds,studentGrades);
        if(students == null)
        {
            ls.logWarn("Query Failed");
            return;
        }
        if(students.length == 0){
            ls.logWarn("No Student Found in the given range " + lower + " to " + higher);
            return;
        }
        ls.logInfo("students in range : ");

        for (int student : students) {
            ls.logInfo(student + " ");
        }
    }
}