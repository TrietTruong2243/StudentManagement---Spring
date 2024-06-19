/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.repository;

import com.example.model.Course_Student;
import com.example.model.Student;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Repository
public interface Course_StudentRepository extends JpaRepository<Course_Student, Integer> {

    //SELECT s FROM Course_Student c  JOIN c.Student s WHERE c.Course_Id = ?1
    @Modifying
    @Query(value = "SELECT s.\"Id\",s.\"Name\",s.\"Birthday\",s.\"Address\",s.\"Notes\" FROM \"Course_Student\" c FULL OUTER JOIN \"Student\" s ON c.\"Student_Id\" = s.\"Id\" WHERE (c.\"Course_Id\" = :cid )", nativeQuery = true)
    List<Object[]> findCourseIds(@Param("cid") Integer cid);

    List<Course_Student> findByCourseId(int course_id, Sort sort);

    List<Course_Student> findByStudentId(int student_id, Sort sort);

    List<Course_Student> findByCourseYear(int year, Sort sort);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM \"Course_Student\" c WHERE (c.\"Student_Id\" = :cid )", nativeQuery = true)
    void deleteByStudentID(@Param("cid") Integer cid);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM \"Course_Student\" c WHERE (c.\"Course_Id\" = :cid )", nativeQuery = true)
    void deleteByCourseID(@Param("cid") Integer cid);
}
