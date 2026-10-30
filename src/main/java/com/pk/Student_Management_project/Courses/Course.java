package com.pk.Student_Management_project.Courses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pk.Student_Management_project.Students.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min=1,max=50)
    @NotBlank(message = "enter valid course name")
    private String courseName;

    @Column(nullable = false,unique = true)
    private String courseCode;

    private String courseDescription;

    @Min(value= 1,message = "credit must be min 1")
    private Integer courseCredit;

    @Builder.Default
    @ManyToMany(mappedBy = "courseList",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnoreProperties("courseList")
    private Set<Student> students=new HashSet<>();

}
