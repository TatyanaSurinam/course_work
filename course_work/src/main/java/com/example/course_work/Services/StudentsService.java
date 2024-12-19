package com.example.course_work.Services;

import java.util.List;
import java.util.Optional;

import com.example.course_work.Models.Grade;
import com.example.course_work.Models.Students;
import com.example.course_work.Repositories.GradeRepository;
import com.example.course_work.Repositories.StudentsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsService {
    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private GradeRepository gradeRepository;

    public List<Students> listAll(String keyword) {
        if (keyword != null) {
            return studentsRepository.search(keyword);
        }
        return studentsRepository.findAll();
    }

    public void save(Students student) {

        studentsRepository.save(student);
    }

    public Students get(Long Id) {
        return studentsRepository.findById(Id).get();
    }


    public void delete(Long Id) {
        studentsRepository.deleteById(Id);
    }

    public List<Students> sortDate() {
        return studentsRepository.sort();
    }

    public Students addGradeToStudent(Long studentId, Grade grade) {
        Optional<Students> studentOptional = studentsRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Students student = studentOptional.get();
            gradeRepository.save(grade);
            student.getGrades().add(grade);
            studentsRepository.save(student);
            return student;
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    public List<Object[]> forTable() {
        return studentsRepository.forTable();
    }

    public Optional<Students> findStudentByUsername(String username) {
        return studentsRepository.findByUser(username);
    }








}
