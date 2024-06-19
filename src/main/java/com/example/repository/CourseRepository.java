/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.repository;

import com.example.model.Course;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ADMIN
 */
@Repository
public interface CourseRepository extends JpaRepository<Course , Integer>{
            List<Course> findByNameLike(String searchname,Sort sort);

        List<Course> findByNameLikeAndYear(String searchname,int searchyear,Sort sort);

}
