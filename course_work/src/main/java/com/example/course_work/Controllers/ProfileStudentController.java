package com.example.course_work.Controllers;

import org.springframework.ui.Model;
import com.example.course_work.Models.Students;
import com.example.course_work.Repositories.StudentsRepository;
import com.example.course_work.Services.StudentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/profile/student")
@PreAuthorize("hasAuthority('STUDENT')")
public class ProfileStudentController {
    @Autowired
    private StudentsService studentsService;
    @GetMapping("/")
    public String getStudentInfo(@RequestParam String username, Model model) {
        Optional<Students> student = studentsService.findStudentByUsername(username);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "student_profile";
        } else {
            return "error";
        }
    }
}
