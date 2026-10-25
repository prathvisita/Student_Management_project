package com.pk.Student_Management_project.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pk.Student_Management_project.Model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDto {
    private Long id;
    private String courseName;
    private String courseCode;
    private String courseDescription;
    private Integer courseCredit;
//    @JsonIgnoreProperties("courses")
//    private Set<Student> student=new HashSet<>();
}
