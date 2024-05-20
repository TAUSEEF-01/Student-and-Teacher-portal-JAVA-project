package com.example.demo;

import java.util.*;

public class Teacher {
    private String Name;
    private String teacher_id;
    private String email;
    private List<Course> courses;

    Teacher(String Name, String teacher_id, String email){
        this.Name = Name;
        this.teacher_id = teacher_id;
        this.email = email;
        courses = new ArrayList<>();
    }

    public String getName(){
        return this.Name;
    }

    public String getTeacher_id(){
        return this.teacher_id;
    }

    public String getEmail(){
        return this.email;
    }
}
