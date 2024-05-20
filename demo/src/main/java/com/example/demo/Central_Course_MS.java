package com.example.demo;

import java.util.*;

public class Central_Course_MS {
    private static List<Course> courses = new ArrayList<>();
    private static Map<String, Course> correspondingCourse = new HashMap<>();
    public static Map<String, String> courseID = new HashMap<>();
    public static Map<Course, List<Student>> studentUnderCouse = new HashMap<>();
    public static List<String> assignments = new ArrayList<>();
    public static List<String> submittedAssignments = new ArrayList<>();

    public static void Initiate(){

        courseID.put("DSA", "2101");
        courseID.put("OOP", "2102");
        courseID.put("EEE", "2103");
        courseID.put("MATH", "2104");
        courseID.put("GED", "2105");

        courseID.put("DSA Lab", "2111");
        courseID.put("OOP Lab", "2112");
        courseID.put("EEE Lab", "2113");
    }

    public static String getCourseID(String courseName){
        return courseID.get(courseName);
    }

    public static void addCourse(String courseName, String courseID, String teacherID){
        Course course = new Course(courseName, courseID, teacherID);
        correspondingCourse.put(courseName, course);
        courses.add(course);

        // Initializing student list for every course
        studentUnderCouse.put(course, new ArrayList<>());

        for(var cour: courses){ // checking
            System.out.print(cour.course_name + " " + cour.course_id + " - ");// checking
        }
        System.out.println();
    }

    public static void removeCourse(String courseName){
        if(correspondingCourse.get(courseName) != null) {
            courses.remove(correspondingCourse.get(courseName));

            for(var cour: courses){ // checking
                System.out.print(cour.course_name + " " + cour.course_id + " - ");// checking
            }
            System.out.println();
        }

    }

    public static void addStudent(String courseName, Student student){
        Course course = correspondingCourse.get(courseName);
        List<Student> students = studentUnderCouse.get(course);
        students.add(student);
    }
}
