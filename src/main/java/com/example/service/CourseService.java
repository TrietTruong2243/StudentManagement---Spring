/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.model.Course;
import com.example.repository.CourseRepository;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
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
public class CourseService {

    private final CourseRepository repository;

    @Autowired
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> getCourses() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "Id"));
    }

    public Course insertCourse(Course course) {
        return repository.save(course);
    }
    
    public Course updateCourse(Course course){
        return repository.save(course);

    }
    public Course findById(int id) {
        return repository.findById(id).isPresent() ?repository.findById(id).get(): null;
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public List<Course> searchCourse(String searchname, String searchyear) {

        List<Course> list = new ArrayList<>();
        if ("".equals(searchyear)) {
            list = repository.findByNameLike('%' + searchname + '%', Sort.by(Sort.Direction.ASC, "Id"));

        } else {
            list = repository.findByNameLikeAndYear('%' + searchname + '%', parseInt(searchyear), Sort.by(Sort.Direction.ASC, "Id"));

        }
        return list;
    }

    public List<Course> getCoursesBySortType(int value, String searchname, String searchyear) {
        List<Course> list = new ArrayList<>();
        switch (value) {
            case 0:
                if ("".equals(searchyear)) {
                    list = repository.findByNameLike('%' + searchname + '%', Sort.by(Sort.Direction.ASC, "Id"));

                } else {
                    list = repository.findByNameLikeAndYear('%' + searchname + '%', parseInt(searchyear), Sort.by(Sort.Direction.ASC, "Id"));

                }
                break;

            case 1:
                if ("".equals(searchyear)) {
                    list = repository.findByNameLike('%' + searchname + '%', Sort.by(Sort.Direction.ASC, "Name"));

                } else {
                    list = repository.findByNameLikeAndYear('%' + searchname + '%', parseInt(searchyear), Sort.by(Sort.Direction.ASC, "Name"));

                }
                break;

            case 2:
                if ("".equals(searchyear)) {
                    list = repository.findByNameLike('%' + searchname + '%', Sort.by(Sort.Direction.DESC, "Name"));

                } else {
                    list = repository.findByNameLikeAndYear('%' + searchname + '%', parseInt(searchyear), Sort.by(Sort.Direction.DESC, "Name"));

                }
                break;

        }
        return list;
    }
}
