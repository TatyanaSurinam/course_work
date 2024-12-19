package com.example.course_work.Controllers;

import com.example.course_work.Models.Grade;
import com.example.course_work.Models.Students;
import com.example.course_work.Services.StudentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/teachers/grades")
@PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
public class TeacherGradeController {
    @Autowired
    private StudentsService studentsService;

    @GetMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) throws IOException {
        try {
            List<Students> listStudents = studentsService.listAll(keyword);
            model.addAttribute("listStudents", listStudents);
            model.addAttribute("keyword", keyword);
            return "teacher_grade";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }


    }

    @GetMapping("/edit/{id}")
    public String showAddGradeForm(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "add_grade"; // Имя HTML-шаблона без расширения
    }

    // Обработка отправки формы для добавления оценки
    @PostMapping("/save/{id}")
    public String addGrade(@PathVariable Long id,
                           @RequestParam int grade,
                           @RequestParam String name_teacher,
                           @RequestParam String subject) {
        Grade newGrade = new Grade(grade, name_teacher, subject);
        studentsService.addGradeToStudent(id, newGrade);
        return "redirect:/teachers/grades/"; // Перенаправление на страницу студента
    }
    @GetMapping("/table")
    public String showTable(Model model) {

        List<Object[]> table = studentsService.forTable();
        model.addAttribute("listConference", table);
        return "student_statistic";
    }







}
