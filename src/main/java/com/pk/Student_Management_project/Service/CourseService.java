package com.pk.Student_Management_project.Service;

import com.pk.Student_Management_project.DTO.CourseDto;
import com.pk.Student_Management_project.Entity.Course;
import com.pk.Student_Management_project.Repository.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepo courseRepo;

    public List<CourseDto> findALL(){
        List<Course> courseList = courseRepo.findAll();
        List<CourseDto> courseDtoList = new ArrayList<>();
        for (Course course : courseList) {
            CourseDto courseDto =CourseDto.builder()
                    .CourseName(course.getCourseName())
                    .CourseCode(course.getCourseCode())
                    .CourseDescription(course.getCourseDescription())
                    .CourseCredit(course.getCourseCredit())
                    .build();
            courseDtoList.add(courseDto);
        }
       return courseDtoList;
    }

    public CourseDto findCourseById(Long courseid){
        Course course=courseRepo.findById(courseid).orElseThrow(()-> new RuntimeException("course with id"+courseid+"not found"));
        CourseDto courseDto=CourseDto.builder()
                .CourseName(course.getCourseName())
                .CourseCode(course.getCourseCode())
                .CourseDescription(course.getCourseDescription())
                .CourseCredit(course.getCourseCredit())
                .build();
        return courseDto;
    }

    public void addCourse(CourseDto courseDto){
        Course course=Course.builder()
                .courseName(courseDto.getCourseName())
                .courseCode(courseDto.getCourseCode())
                .courseDescription(courseDto.getCourseDescription())
                .courseCredit(courseDto.getCourseCredit())
                .build();
        courseRepo.save(course);
    }

    public void deleteCourseById(Long courseid){
        if(courseRepo.findById(courseid).isPresent()){
            courseRepo.deleteById(courseid);
        }else{
            throw new RuntimeException("course with id"+courseid+" not found");
        }
    }

    public CourseDto updateCourse(Long courseid,CourseDto courseDto){
        Course course=courseRepo.findById(courseid).orElseThrow(()-> new RuntimeException("course with id"+courseid+"not found"));
        course.setCourseName(courseDto.getCourseName());
        course.setCourseCode(courseDto.getCourseCode());
        course.setCourseDescription(courseDto.getCourseDescription());
        course.setCourseCredit(courseDto.getCourseCredit());


        CourseDto coursedto= findCourseById(courseid);
        coursedto.setCourseName(course.getCourseName());
        coursedto.setCourseCode(course.getCourseCode());
        coursedto.setCourseDescription(course.getCourseDescription());
        coursedto.setCourseCredit(course.getCourseCredit());
        courseRepo.save(course);
        return coursedto;
    }


}
