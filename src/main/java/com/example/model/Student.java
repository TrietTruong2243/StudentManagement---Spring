/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor; 
import lombok.Builder; 
import lombok.Data; 
import lombok.NoArgsConstructor; 
  
// Importing required classes  
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
@Entity
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"Student\"")
@Data
public class Student {
    @Id
    @Column(name = "\"Id\"", nullable = false)
    int id;
    @Column(name = "\"Name\"", nullable = false)
    String name;
    @Column(name = "\"Birthday\"", nullable = false)
    LocalDate birthday;
    @Column(name = "\"Address\"", nullable = false)
    String address;
    @Column(name = "\"Notes\"")
    String notes;
//    public Student(){
//        
//    }
    public Student(int id, String name,LocalDate birthday, String address, String notes){
        super();
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.notes = notes;
    }
//    
//    public void setId(int id)
//    {
//        this.id = id;
//    }
//    
//    public void setName(String name)
//    {
//        this.name = name;
//    }
//    public void setBirthday(Date birthday)
//    {
//        this.birthday = birthday;
//    }
//    
//    public void setAddress(String address)
//    {
//        this.address = address;
//    }
//    public void setNotes(String notes)
//    {
//        this.notes = notes;
//    }
//    
//    public int getId(){
//        return id;
//    }
//    
//    public String getName(){
//        return name;
//    }
//    public Date getBirthday(){
//        return birthday;
//    }
//    
//    public String getAddress()
//    {
//        return address;
//    }
//    public String getNotes(){
//        return notes;
//    }
}
