package com.pk.Student_Management_project.DTO;

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
    private Set<StudentResponseDto> studentResponseDtoList=new HashSet<>();
}
