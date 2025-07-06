package com.jdbc;

import java.util.Scanner;

import com.jdbc.dao.CourseDao;
import com.jdbc.dao.EnrollmentDao;
import com.jdbc.dao.StudentDao;
import com.jdbc.model.Enrollment;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.println("WELCOME TO MY SMALL JDBC PROJECT : student course management system !!!!\n\n");

            System.out.println("Functionalities that I provide : ");

            while (true) {
                System.out.println("1. Add a new student \n2. Add a new course \n3. Enroll a student in a course \n4. View all enrollments \n5. Delete a student \n6. Delete a course \n7. Show all courses \n8. Show all students \n9. Delete an Enrollment \n10. Delete multiple students in batch \n11. Delete multiple courses in batches \n12. Delete multiple Enrollments \n\n");
                System.out.print("Enter your choice (1,2,3,..etc) : ");
                int choice = sc.nextInt();
                System.out.println("\n");

                if(choice == 1){
                    StudentDao st = new StudentDao();
                    st.addStudent(sc);
                }else if(choice == 2){
                    CourseDao cs = new CourseDao();
                    sc.nextLine();
                    cs.addCourse(sc);
                    sc.nextLine();
                }else if(choice == 3){
                    EnrollmentDao en = new EnrollmentDao();
                    en.addEnrollment(sc);
                }else if(choice == 4){
                    Enrollment en = new Enrollment();
                    en.showEnrollments();
                }else if(choice == 5){
                    StudentDao st = new StudentDao();
                    st.deleteStudent(sc);
                }else if(choice == 6){
                    CourseDao cs = new CourseDao();
                    sc.nextLine();
                    cs.deleteCourse(sc);
                    sc.nextLine();
                }else if(choice == 7){
                    CourseDao cs = new CourseDao();
                    System.out.println("Course Table : ");
                    cs.showCourses();
                }else if(choice == 8){
                    StudentDao cs = new StudentDao();
                    System.out.println("Student Table : ");
                    cs.showStudents();
                }else if(choice == 9){
                    EnrollmentDao en = new EnrollmentDao();
                    en.deleteEnrollment(sc);
                }else if(choice == 10){
                    StudentDao st = new StudentDao();
                    st.deleteStudents(sc);
                }else if(choice == 11){
                    CourseDao cs = new CourseDao();
                    cs.deleteCourses(sc);
                }else if(choice == 12){
                    EnrollmentDao en = new EnrollmentDao();
                    en.deleteEnrollments(sc);
                }


                System.out.println("\n\nYou want to do any new operation (Y/N) ? : ");
                String yesorno = sc.next();
                if (yesorno.toUpperCase().equals("N")){
                    System.out.println("Thank You Visit Again!!");
                    break;}
            }

        } finally {
            sc.close();
        }

    }
}
