package com.pk.Student_Management_project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    @NotBlank(message = "please enter student name")
    private String firstName;


    @Column(nullable = false,length = 50)
    @NotBlank(message = "please enter last name")
    private String lastName;

    @Column(nullable = false,length = 100)
    @Email(message = "Enter valid email")
    private String email;

    @Column(nullable = false)
    private Long mobileNumber;

   @ManyToMany(fetch = FetchType.LAZY)
   @Builder.Default
   @JoinTable(name="student-course",
             joinColumns =  @JoinColumn(name = "student_id"),
           inverseJoinColumns = @JoinColumn(name = "course_id")
   )
   @JsonIgnoreProperties("students")
   private Set<Course> courseList=new HashSet<>();



}
