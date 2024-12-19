package com.example.course_work.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int grade;
    private String name_teacher;
    private String subject;


    public Grade(int grade, String name_teacher, String subject) {
        this.grade = grade;
        this.name_teacher = name_teacher;
        this.subject = subject;
    }

    public Grade() {

    }
}
