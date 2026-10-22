package com.pk.Student_Management_project.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {
    @NotBlank(message = "Enter valid name")
    private String firstName;

    @NotBlank(message = "Enter valid last name")
    private String lastName;

    @NotBlank(message = "Enter valid email")
    private String email;

    @NotNull(message = "mobile number is invalid")
    private Long mobileNumber;
}
