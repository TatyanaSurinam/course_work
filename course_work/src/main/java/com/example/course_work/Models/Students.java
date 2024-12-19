package com.example.course_work.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "email")
    private String email;
    @Column(name = "date_of_birth")
    private String date_of_birth;
    @Column(name = "date_of_enrollment")
    private String date_of_enrollment;
    @Column(name = "student_group")
    private String studentGroup;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "students_grade",
            joinColumns = @JoinColumn(name = "students_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id")
    )
    private Set<Grade> grades;


    public Students() {};

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_student",
            joinColumns = @JoinColumn(name = "students_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private User user;







    public String toString(){
        return "students [id=" + id + " surname=" + surname + " name=" + name + " patronymic=" + patronymic + " email=" + email + " date_of_birth=" + date_of_birth + " date_of_enrollment=" + date_of_enrollment + " group=" + studentGroup + "]";
    }


}
