/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.model.Course_Student;
import com.example.model.Student;
import com.example.service.CourseService;
import com.example.service.Course_StudentService;
import com.example.service.StudentService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.Integer.parseInt;
import static java.lang.System.in;
import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static org.apache.tomcat.util.http.FastHttpDateFormat.parseDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ADMIN
 */
@Controller
@RequestMapping("student")
public class StudentController {

//    private final static String url = "jdbc:postgresql://localhost:5432/ClassManagement";
//    private final static String username = "postgres";
//    private final static String password = "123456";
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private Course_StudentService course_studentService;
//    @Autowired
//    public StudentController(StudentService service) {
//        this.service = service;
//    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static java.sql.Date convertFromJAVADateToSQLDate(
            java.util.Date javaDate) {
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = (java.sql.Date) new Date(javaDate.getTime());
        }
        return sqlDate;
    }

    @GetMapping("")
    public String index(Model model) throws ParseException {

        List<Student> studentList = studentService.getStudents();
        for (Student i : studentList) {
            i.setBirthday(i.getBirthday());
        }
        model.addAttribute("studentList", studentList);
        return "student/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(HttpServletRequest request, RedirectAttributes rm, Model model) throws ParseException {

        System.out.print(request.getParameter("id"));
        Student std = new Student(parseInt(request.getParameter("id")), request.getParameter("name"), LocalDate.parse(request.getParameter("birthday"), formatter), request.getParameter("address"), request.getParameter("notes"));
        Student checkSTD = studentService.findById(parseInt(request.getParameter("id")));
        if (checkSTD == null) {
            Student res = studentService.insertStudent(std);

        } else {
            rm.addFlashAttribute("adderr", true);
            rm.addAttribute("adderr", true);
        }
        return "redirect:/student";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {

        Student std = null;
        std = studentService.findById(Math.toIntExact(id));
        List<Course_Student> cs = course_studentService.findCourseInStudent(Math.toIntExact(id));
        model.addAttribute("courseList", cs);
        model.addAttribute("student", std);
        return "student/student";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteStudent(HttpServletRequest request, Model model) throws ParseException {
        int id = parseInt(request.getParameter("id"));
        course_studentService.deleteByStudentID(id);
        studentService.deleteById(id);
        return "redirect:/student";
    }

    @RequestMapping(value = "/changesorttype", method = RequestMethod.POST)
    public @ResponseBody
    List<Student> changeSortType(@RequestBody String value, HttpServletRequest request, Model model) throws ParseException, JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//
//        Object parser = mapper.readValue(value, Object.class);
//
//        int value1 = parser.;
        org.springframework.boot.json.JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> jsonMap = jsonParser.parseMap(value);
        int value1 = parseInt((String) jsonMap.get("value"));
        String searchValue = (String) jsonMap.get("searchValue");
        List<Student> list = studentService.getStudentsBySortType(value1, searchValue);
        model.addAttribute("studentList", list);
        return list;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateStudent(HttpServletRequest request, Model model) throws ParseException {

        int id = parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        LocalDate lecture = LocalDate.parse(request.getParameter("birthday"), formatter);
        String address = request.getParameter("address");
        String notes = request.getParameter("notes");
        Student std = new Student(id, name, lecture, address, notes);
        Student res = studentService.updateStudent(std);
        return "redirect:/student/" + id;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchStudent(HttpServletRequest request, Model model) throws ParseException {
        String valueSearch = request.getParameter("searchValue");
        List<Student> list = studentService.searchStudents(valueSearch);
//        System.out.println(list);
//        model.addAttribute("studentList", list);
        model.addAttribute("search_value", valueSearch);

        model.addAttribute("studentList", list);
        return "student/index";
    }

    @RequestMapping(value = "/year", method = RequestMethod.GET)
    public String studentFromYear(HttpServletRequest request, Model model) throws ParseException {
//        String valueSearch = request.getParameter("year");
        List<Course_Student> list = course_studentService.findAll();
//        System.out.println(list);
//        model.addAttribute("studentList", list);
//        model.addAttribute("search_value", valueSearch);
//
        model.addAttribute("studentList", list);
        return "student/year";
    }

    @RequestMapping(value = "/year/search", method = RequestMethod.GET)
    public String studentFromYearSearch(HttpServletRequest request, Model model) throws ParseException {
        if ("".equals(request.getParameter("year"))){
             List<Course_Student> list = course_studentService.findAll();
//        model.addAttribute("year", year);
        model.addAttribute("studentList", list);
        }
        else{
            int year = parseInt(request.getParameter("year"));
        List<Course_Student> list = course_studentService.findByCourseYear(year);
        model.addAttribute("year", year);
        model.addAttribute("studentList", list);
        }
        
        return "student/year";
    }

}
