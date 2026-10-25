package com.pk.Student_Management_project.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pk.Student_Management_project.Model.Course;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StudentResponseDto {

    private Long  id;
    private String firstName;
    private String lastName;
    private String email;
    private Long mobileNumber;
//  @JsonIgnoreProperties("student")
//   private Set<Course> courses=new HashSet<>();


}
