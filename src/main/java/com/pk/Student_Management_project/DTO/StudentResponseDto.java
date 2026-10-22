package com.pk.Student_Management_project.DTO;

import com.pk.Student_Management_project.Entity.Course;
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
public class StudentResponseDto {

    private Long  id;
    private String firstName;
    private String lastName;
    private String email;
    private Long mobileNumber;

    private Set<Course> courses=new HashSet<>();


}
