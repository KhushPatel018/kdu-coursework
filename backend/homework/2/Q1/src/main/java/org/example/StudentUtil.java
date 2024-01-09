package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentUtil {
    /**
     * calculate gpa by averaging the points in courses
     * @param studentIdList student ids
     * @param studentsGrades grade list for each student
     * @return gpa
     */
    public static double[] calculateGPA(int[] studentIdList, char[][]
            studentsGrades) {
        int noOfStudents = studentIdList.length;
        double[] gpaList = new double[noOfStudents];
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
                points += gradesToPoints.getOrDefault("" + (studentsGrades[i][j]), 0);
            }
            gpaList[i] = (double) points / (double) totalCourses;
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
            studentIdList, char[][] studentsGrades) {
        double[] gpaList = calculateGPA(studentIdList,studentsGrades);
        int noOfStudents = studentIdList.length;

        ArrayList<Integer> validStudents = new ArrayList<Integer>();
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
            studentsInRange[i] = (int)validStudents.get(i);
        }
        return studentsInRange;
    }
}
