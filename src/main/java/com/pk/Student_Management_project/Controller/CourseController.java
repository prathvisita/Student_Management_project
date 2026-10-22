package com.pk.Student_Management_project.Controller;

import com.pk.Student_Management_project.DTO.CourseDto;
import com.pk.Student_Management_project.Entity.Course;
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
    public ResponseEntity<List<CourseDto>> getAllCourses(){
     return ResponseEntity.ok(courseService.findALL());
    }

    @GetMapping("/{courseiD}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long courseiD){
        return ResponseEntity.ok().body(courseService.findCourseById(courseiD));
    }

    @PostMapping
    public ResponseEntity<Void> addCourse(@Valid @RequestBody CourseDto courseDto){
        courseService.addCourse(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
   @DeleteMapping("/{courseid}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable Long courseid){
        courseService.deleteCourseById(courseid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }

   @PutMapping("/{courseid}")
    public ResponseEntity<CourseDto>updateCourse(@PathVariable Long Courseid,@Valid @RequestBody CourseDto courseDto){
        return ResponseEntity.ok().body(courseService.updateCourse(Courseid,courseDto));
   }
}
