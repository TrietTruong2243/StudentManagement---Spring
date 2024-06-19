package com.example.service;

import com.example.model.Student;
import com.example.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
@Service
public class StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getStudents() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "Id"));
    }

    public List<Student> searchStudents(String value) {
        System.out.println(value);
        List<Student> list = new ArrayList<>();
        list = repository.findByNameLike('%' + value + '%', Sort.by(Sort.Direction.ASC, "Id"));
        return list;
    }

    public List<Student> getStudentsBySortType(int value, String searchValue) {
        List<Student> list = new ArrayList<>();
        switch (value) {
            case 0:
                list = repository.findByNameLike('%' + searchValue + '%', Sort.by(Sort.Direction.ASC, "Id"));
                break;

            case 1:
                list = repository.findByNameLike('%' + searchValue + '%', Sort.by(Sort.Direction.ASC, "Name"));
                break;

            case 2:
                list = repository.findByNameLike('%' + searchValue + '%', Sort.by(Sort.Direction.DESC, "Name"));
                break;

        }
        return list;
    }

    public Student insertStudent(Student std) {
        return repository.save(std);
    }

    public Student updateStudent(Student std) {
        return repository.save(std);
    }

    public Student findById(int id) {
        return repository.findById(id).isPresent() ?repository.findById(id).get(): null;
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public void update(Student std) {
        repository.save(std);
    }

}
