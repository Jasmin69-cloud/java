package com.example.studentmgmt.model;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String courseName;
    private String duration;

    public Course() {}

    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    // Getters and setters
    public int getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
}
