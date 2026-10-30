package com.pk.Student_Management_project.Courses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
