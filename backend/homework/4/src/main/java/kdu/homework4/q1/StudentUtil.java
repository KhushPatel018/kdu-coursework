package kdu.homework4.q1;

import kdu.homework4.logging.LoggingSystem;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentUtil {

    private StudentUtil(){

    }
    private static final LoggingSystem ls = new LoggingSystem();
    /**
     * calculate gpa by averaging the points in courses
     * @param studentIdList student ids
     * @param studentsGrades grade list for each student
     * @return gpa
     */

    public static double[] calculateGPA(int[] studentIdList, char[][]
            studentsGrades) throws MissingGradeException {

        int noOfStudents = studentIdList.length;
        double[] gpaList = new double[noOfStudents];
        try{
            // part of question1 task1
            if(noOfStudents != studentsGrades.length){
                throw new IllegalArgumentException("studentIdList & studentsGrades are out-of-sync. studentIdList.length: " + studentIdList.length + ", studentsGrades.length: " + studentsGrades.length);
            }
            HashMap<String,Integer> gradesToPoints = new HashMap<>();
            gradesToPoints.put("A",4);
            gradesToPoints.put("B",3);
            gradesToPoints.put("C",2);
            gradesToPoints.put("D",1);

            for(int i = 0;i < noOfStudents;i++)
            {
                int points = 0;
                int totalCourses = studentsGrades[i].length;
                for(int j = 0;j < totalCourses;j++)
                {
                    if(studentsGrades[i][j] == ' '){
                        throw new MissingGradeException(studentIdList[i]);
                    }else points += gradesToPoints.getOrDefault("" + (studentsGrades[i][j]), 0);
                }
                gpaList[i] = (double) points / (double) totalCourses;
            }
        }
        catch (IllegalArgumentException e){
            ls.logWarn(e.getMessage());
        }
        return gpaList;
    }

    /**
     * gives student list between lower and higher
     * @param lower lower bound of query
     * @param higher upper bound of query
     * @param studentIdList student ids
     * @param studentsGrades grade list for each student
     * @return list of student who have gpa in range
     */
    public static int[] getStudentsByGPA(double lower, double higher, int[]
            studentIdList, char[][] studentsGrades) throws InvalidDataException {
        int noOfStudents = studentIdList.length;
        double[] gpaList = new double[noOfStudents];
        try{
            gpaList = calculateGPA(studentIdList,studentsGrades);
        }
        catch (MissingGradeException e){
            throw new InvalidDataException(e.getMessage());
        }

            ArrayList<Integer> validStudents = new ArrayList<>();
            for(int i = 0;i < noOfStudents;i++)
            {
                double gpa = gpaList[i];
                if(gpa <= higher && gpa >= lower)
                {
                    validStudents.add(studentIdList[i]);
                }
            }
        int[] studentsInRange = new int[validStudents.size()];
        for(int i = 0;i < validStudents.size();i++)
        {
            studentsInRange[i] = validStudents.get(i);
        }
        return studentsInRange;
    }

  public static void main(String[] args) {

        int[] studentIds =  {1001, 1002};
        char[][] studentGrades = { { 'A', 'A', 'A', 'B' },{'A', 'B','A'}} ;
        double lower = 3.2;
        double higher = 3.5;
        int[] students = StudentUtil.getStudentsByGPA(lower,higher,studentIds,studentGrades);

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