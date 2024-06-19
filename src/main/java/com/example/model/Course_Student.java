/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"Course_Student\"")
@Data
public class Course_Student {
    @Id
    @Column(name = "\"Id\"", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)        
    int id;
    @ManyToOne
    @JoinColumn(name = "`Student_Id`")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "`Course_Id`")
    private Course course;
    @Column(name = "\"Grade\""  ,nullable = true)
    float grade;
    
}
