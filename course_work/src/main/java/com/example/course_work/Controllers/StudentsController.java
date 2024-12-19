package com.example.course_work.Controllers;

import java.io.IOException;
import java.util.List;

import com.example.course_work.Models.Students;
import com.example.course_work.Services.StudentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/admin/students")
@PreAuthorize("hasAuthority('ADMIN')")
public class StudentsController {
    @Autowired
    private StudentsService studentsService;

    @GetMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) throws IOException {
        try {
            List<Students> listStudents = studentsService.listAll(keyword);
            model.addAttribute("listStudents", listStudents);
            model.addAttribute("keyword", keyword);
            return "students";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/new")
    public String showNewStudentsForm(Model model) {
        Students student = new Students();
        model.addAttribute("student", student);
        return "new_students";
    }

    @PostMapping("/save")
    public String saveStudents(@ModelAttribute("student") Students student) {
        studentsService.save(student);
        return "redirect:/admin/students/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditStudentsForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_students");
        Students student = studentsService.get(id);
        mav.addObject("student", student);
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteStudents(@PathVariable(name = "id") Long id) {
        studentsService.delete(id);
        return "redirect:/admin/students/";
    }

    @GetMapping("/sort")
    public String sortStudents(Model model) {
        List<Students> sortedList = studentsService.sortDate();
        model.addAttribute("listStudents", sortedList);
        return "students";
    }


}
