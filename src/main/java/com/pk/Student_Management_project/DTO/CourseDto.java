package com.pk.Student_Management_project.DTO;

import com.pk.Student_Management_project.Entity.Student;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    @NotBlank(message = "Enter course name")
    private String CourseName;

    @NotBlank(message = "Enter course credit")
    private String CourseCode;

    @NotBlank(message = "enter course description")
    private String CourseDescription;

    //@NotBlank(message = "Enter course credit")
    @Min(value = 1,message = "credit must be min 1")
    private Integer CourseCredit;




}
