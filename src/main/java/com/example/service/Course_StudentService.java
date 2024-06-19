/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.model.Course;
import com.example.model.Course_Student;
import com.example.model.Student;
import com.example.repository.Course_StudentRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class Course_StudentService {
    private final Course_StudentRepository repository;
    
    @Autowired
    public Course_StudentService(Course_StudentRepository repository) {
        this.repository = repository;
    }
    
//    public List<Course_Student> findByCourseId(int course_id)
//    {
//        return repository.
//    }
    public Course_Student findCourseStudent(int id)
    {
        return repository.findById(id).get();
    }
    public List<Course_Student> findStudentInCourse(int course_id)
    {
        List<Course_Student> res =repository.findByCourseId(course_id,Sort.by(Sort.Direction.ASC, "student.Id"));
        return res;
    }
    public List<Course_Student> findCourseInStudent(int student_id)
    {
        List<Course_Student> res =repository.findByStudentId(student_id,Sort.by(Sort.Direction.ASC, "course.Id"));
        return res;
    }
    public Course_Student addStudentToCourse(Course course, Student student)
    {
        Course_Student res= new Course_Student();
        res.setCourse(course);
        res.setStudent(student);
        return repository.save(res);
    }
    
    public Course_Student updateStudentInCourse(Course_Student course_student)
    {
        return repository.save(course_student);
    }
    
    public void deleteCourseStudent(int csid)
    {
        repository.deleteById(csid);
    }
     public List<Course_Student> findAll(){
        return repository.findAll();
    }
    public List<Course_Student> findByCourseYear(int year){
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "student.id").ignoreCase());

        return repository.findByCourseYear(year,sort);
    }
    
    public void deleteByStudentID(int id)
    {
        repository.deleteByStudentID(id);
    }
    
      public void deleteByCourseID(int id)
    {
        repository.deleteByCourseID(id);
    }
}
