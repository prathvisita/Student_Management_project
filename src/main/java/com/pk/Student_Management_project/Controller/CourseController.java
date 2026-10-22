package com.pk.Student_Management_project.Controller;

import com.pk.Student_Management_project.DTO.CourseRequestDto;
import com.pk.Student_Management_project.DTO.CourseResponseDto;
import com.pk.Student_Management_project.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@Validated
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses(){
     return ResponseEntity.ok(courseService.findALL());
    }

    @GetMapping("/{courseid}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long courseid){
        return ResponseEntity.ok().body(courseService.findCourseById(courseid));
    }

    @PostMapping
    public ResponseEntity<Void> addCourse(@Valid @RequestBody CourseRequestDto courseRequestDto){
        courseService.addCourse(courseRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
   @DeleteMapping("/{courseid}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable Long courseid){
        courseService.deleteCourseById(courseid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }

   @PutMapping("/{courseid}")
    public ResponseEntity<CourseResponseDto>updateCourse(@PathVariable Long Courseid, @Valid @RequestBody CourseRequestDto courseRequestDto){
        return ResponseEntity.ok().body(courseService.updateCourse(Courseid, courseRequestDto));
   }
}
