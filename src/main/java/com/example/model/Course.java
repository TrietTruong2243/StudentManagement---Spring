/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "\"Course\"")
@Data
public class Course {
    @Id
    @Column(name = "\"Id\"", nullable = false)
    int id;
    @Column(name = "\"Name\"", nullable = false)
    String name;
    @Column(name = "\"Lecture\"", nullable = false)
    String lecture;
    @Column(name = "\"Year\"", nullable = false)
    int year;    
    @Column(name = "\"Notes\"")
    String notes;
 
}
