package org.kdu.JavaHandOn1;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.\
enum Choice {
    ADD,
    RETRIEVE,
    UPDATE
}

enum Retrieval{
    RETRIEVAL_BY_ID,
    RETRIEVAL_BY_NAME,
    RETRIEVAL_BY_ID_AND_NAME
}

enum UpdateOptions{
    UPDATE_ALL,
    UPDATE_AGE,
    UPDATE_NAME
}

public class Main {

    public static int takeIdInput(Scanner sc)
    {
        int id;
        System.out.print("Enter the id : ");
        id = sc.nextInt();
        sc.nextLine();
        return id;
    }

    public static int takeAgeInput(Scanner sc)
    {
        int age;
        System.out.print("Enter the age : ");
        age = sc.nextInt();
        return age;
    }

    public static String takeNameInput(Scanner sc)
    {
        String name;
        System.out.print("Enter the name : ");
        name = sc.nextLine();
        return name;
    }

    public static String takeGradeInput(Scanner sc)
    {
        String grade;
        System.out.print("Enter the Grade : ");
        grade = sc.nextLine();
        return grade;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        // initializing Student Repository
        StudentRepository Register = new StudentRepository();

        int age;
        int id;
        String grade;
        String name;


        boolean gate = true;
        while(gate) {

            System.out.println("Enter the functionality you want to use \n A) ADD B) RETRIEVE C) UPDATE");
            char selection = sc.nextLine().charAt(0);
            Choice option = selection == 'A' ? Choice.ADD : ( selection == 'B' ? Choice.RETRIEVE : Choice.UPDATE );

            switch (option) {
                case ADD -> {
                    System.out.println("You want to add student to Student Repository so please provide the student details \n");
                    id = takeIdInput(sc);
                    name = takeNameInput(sc);
                    age = takeAgeInput(sc);
                    sc.nextLine();
                    grade = takeGradeInput(sc);
                    Register.add(new Student(id, name, age, grade));
                }
                case RETRIEVE -> {
                    System.out.println("We provide 3 types of retrieval \n A) RETRIEVAL_BY_ID, B) RETRIEVAL_BY_NAME, C) RETRIEVAL_BY_ID_AND_NAME");
                    selection = sc.nextLine().charAt(0);
                    Retrieval Type = selection == 'A' ? Retrieval.RETRIEVAL_BY_ID : (selection == 'B' ? Retrieval.RETRIEVAL_BY_NAME : Retrieval.RETRIEVAL_BY_ID_AND_NAME);

                    switch (Type) {
                        case RETRIEVAL_BY_ID -> {
                            id = takeIdInput(sc);
                            Student retrieved_student = Register.retrieve(id);
                            retrieved_student.printStudent();
                        }
                        case RETRIEVAL_BY_NAME -> {
                            name = takeNameInput(sc);
                            ArrayList<Student> students = Register.retrieve(name);
                            for (Student retrieved_student : students) {
                                retrieved_student.printStudent();
                            }
                        }
                        case RETRIEVAL_BY_ID_AND_NAME -> {
                            id = takeIdInput(sc);
                            name = takeNameInput(sc);
                            Student retrieved_student = Register.retrieve(id, name);
                            retrieved_student.printStudent();
                        }
                    }
                }
                case UPDATE -> {
                    System.out.println("We provide 3 types of Updates \n A) UPDATE_ALL, B) UPDATE_AGE, C) UPDATE_NAME");
                    selection = sc.nextLine().charAt(0);
                    UpdateOptions Type = selection == 'A' ? UpdateOptions.UPDATE_ALL : (selection == 'B' ? UpdateOptions.UPDATE_AGE : UpdateOptions.UPDATE_NAME);

                    switch (Type) {
                        case UPDATE_ALL -> {
                            id = takeIdInput(sc);
                            name = takeNameInput(sc);
                            age = takeAgeInput(sc);
                            grade = takeGradeInput(sc);
                            Student updated_student = Register.update(id, name, age, grade);
                            updated_student.printStudent();
                        }
                        case UPDATE_AGE -> {
                            id = takeIdInput(sc);
                            age = takeAgeInput(sc);
                            Student updated_student = Register.update(id, age);
                            updated_student.printStudent();
                        }
                        case UPDATE_NAME -> {
                            id = takeIdInput(sc);
                            name = takeNameInput(sc);
                            Student updated_student = Register.update(id, name);
                            updated_student.printStudent();
                        }
                    }
                }
                default -> System.out.println("Choose available Functionality Please");
            }

            System.out.print("DO YOU WANT TO CONTINUE ? Y/N ");
            selection = sc.nextLine().charAt(0);
            if(selection == 'N' || selection == 'n'){
                System.out.println("Thank You");
                gate = false;
            }
        }
    }
}