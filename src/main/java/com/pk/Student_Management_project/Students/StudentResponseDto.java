package com.pk.Student_Management_project.Students;

import lombok.*;

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
