package com.pk.Student_Management_project.Controller;

import com.pk.Student_Management_project.DTO.CourseDto;
import com.pk.Student_Management_project.DTO.StudentDto;
import com.pk.Student_Management_project.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

   @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/student/{studentid}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long studentid) {
       return  ResponseEntity.ok(studentService.findById(studentid));
    }

    @PostMapping("/student")
    public ResponseEntity<Void> addStudent(@Valid @RequestBody StudentDto studentDto) {
       studentService.createStudent(studentDto);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/student/{studentid}")
    public ResponseEntity<StudentDto>updateStudent(@PathVariable Long studentid,@Valid @RequestBody StudentDto studentDto) {
       return ResponseEntity.ok(studentService.updateStudent(studentid, studentDto));
    }

    @DeleteMapping("/student/{studentid}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentid) {
       studentService.deleteStudent(studentid);
       return ResponseEntity.noContent().build();
    }

    @PostMapping("/student/{studentid}/course/{courseid}")
    public ResponseEntity<StudentDto> addCourse(@PathVariable Long studentid, @PathVariable Long courseid) {
       return ResponseEntity.ok(studentService.enrollToCourse(studentid,courseid));
    }

    @DeleteMapping("/student/{studentid}/course/{courseid}")
    public ResponseEntity<StudentDto> deleteCourse(@PathVariable Long studentid, @PathVariable Long courseid) {
        return ResponseEntity.ok(studentService.unenrollToCourse(studentid,courseid));
    }
}
