/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.model.Course;
import com.example.model.Course_Student;
import com.example.model.Student;
import com.example.service.CourseService;
import com.example.service.Course_StudentService;
import com.example.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ADMIN
 */
@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private Course_StudentService course_studentService;

    @GetMapping("")
    public String index(Model model) throws ParseException {

        List<Course> courseList = courseService.getCourses();
        model.addAttribute("courseList", courseList);
        return "course/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(HttpServletRequest request,RedirectAttributes rm, Model model) throws ParseException {
        System.out.print(request.getParameter("id"));
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Course course = new Course(parseInt(request.getParameter("id")), request.getParameter("name"), request.getParameter("lecture"), parseInt(request.getParameter("year")), request.getParameter("notes"));
        Course checkCourse = courseService.findById(parseInt(request.getParameter("id")));
        if (checkCourse == null) {
        Course res = courseService.insertCourse(course);

        } else {
            rm.addFlashAttribute("adderr", true);
            rm.addAttribute("adderr", true);
        }
        return "redirect:/course";
    }

    @RequestMapping(value = "/{id}/add_student", method = RequestMethod.POST)
    public String addStudent(@PathVariable Long id, HttpServletRequest request, Model model) {
        Course course = courseService.findById(Math.toIntExact(id));
        Student student = studentService.findById(parseInt(request.getParameter("student_id")));
        Course_Student c_std = course_studentService.addStudentToCourse(course, student);
        return "redirect:/course/" + id;
    }

    @RequestMapping(value = "/{id}/update_grade", method = RequestMethod.POST)
    public String updateStudentGrade(@PathVariable Long id, HttpServletRequest request, Model model) {
        int csid = parseInt(request.getParameter("csid"));
        Course_Student res = course_studentService.findCourseStudent(csid);
        res.setGrade(parseFloat(request.getParameter("grade")));
        Course_Student c_std = course_studentService.updateStudentInCourse(res);
        return "redirect:/course/" + id;
    }

    @RequestMapping(value = "/{id}/delete_student", method = RequestMethod.POST)
    public String deleteStudentFromCourse(@PathVariable Long id, HttpServletRequest request, Model model) {
        int csid = parseInt(request.getParameter("csid"));
        course_studentService.deleteCourseStudent(csid);
//        res.setGrade(parseFloat(request.getParameter("grade")));
//        Course_Student c_std = course_studentService.updateStudentInCourse(res);
        return "redirect:/course/" + id;
    }

    @GetMapping("/{id}")
    public String getCourse(@PathVariable Long id, Model model) {

        Course course = courseService.findById(Math.toIntExact(id));
        List<Student> stdAddList = studentService.getStudents();
        List<Course_Student> stdInCourse = course_studentService.findStudentInCourse(Math.toIntExact(id));
        for (Course_Student i : stdInCourse) {
            stdAddList.remove(i.getStudent());
        }
        System.out.print(stdInCourse);
        model.addAttribute("course", course);
        model.addAttribute("studentInCourseList", stdInCourse);
        model.addAttribute("studentAddList", stdAddList);
        return "course/course";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteCourse(HttpServletRequest request, Model model) throws ParseException {

        int id = parseInt(request.getParameter("id"));
        course_studentService.deleteByCourseID(id);
        courseService.deleteById(id);
        return "redirect:/course";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCourse(HttpServletRequest request, Model model) throws ParseException {

        int id = parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String lecture = request.getParameter("lecture");
        int year = parseInt(request.getParameter("year"));
        String notes = request.getParameter("notes");
        Course course = new Course(id,name,lecture,year, notes);
        Course res = courseService.updateCourse(course);
        return "redirect:/course/" + id;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchStudent(HttpServletRequest request, Model model) throws ParseException {
        String searchyear = request.getParameter("searchyear");
        String searchname = request.getParameter("searchname");

        List<Course> list = courseService.searchCourse(searchname, searchyear);

        model.addAttribute("searchname", searchname);
        model.addAttribute("searchyear", searchyear);

        model.addAttribute("courseList", list);
        return "course/index";
    }

    @RequestMapping(value = "/changesorttype", method = RequestMethod.POST)
    public @ResponseBody
    List<Course> changeSortType(@RequestBody String value, HttpServletRequest request, Model model) throws ParseException, JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//
//        Object parser = mapper.readValue(value, Object.class);
//
//        int value1 = parser.;
        org.springframework.boot.json.JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> jsonMap = jsonParser.parseMap(value);
        int value1 = parseInt((String) jsonMap.get("value"));
        String searchname = (String) jsonMap.get("searchname");
        String searchyear = (String) jsonMap.get("searchyear");

        List<Course> list = courseService.getCoursesBySortType(value1, searchname, searchyear);
        model.addAttribute("courseList", list);
        return list;
    }
}
