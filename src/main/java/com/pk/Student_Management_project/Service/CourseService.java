package com.pk.Student_Management_project.Service;

import com.pk.Student_Management_project.DTO.CourseRequestDto;
import com.pk.Student_Management_project.DTO.CourseResponseDto;
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

    public List<CourseResponseDto> findALL(){
        List<Course> courseList = courseRepo.findAll();
        List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();
        for (Course course : courseList) {
            CourseResponseDto courseResponseDto = CourseResponseDto.builder()
                    .id(course.getId())
                    .courseName(course.getCourseName())
                    .courseCode(course.getCourseCode())
                    .courseDescription(course.getCourseDescription())
                    .courseCredit(course.getCourseCredit())
                    .build();
            courseResponseDtoList.add(courseResponseDto);
        }
       return courseResponseDtoList;
    }

    public CourseResponseDto findCourseById(Long courseid){
        Course course=courseRepo.findById(courseid).orElseThrow(()-> new RuntimeException("course with id"+courseid+"not found"));
        CourseResponseDto courseResponseDto = CourseResponseDto.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .courseCode(course.getCourseCode())
                .courseDescription(course.getCourseDescription())
                .courseCredit(course.getCourseCredit())
                .build();
        return courseResponseDto;
    }

    public void addCourse(CourseRequestDto courseRequestDto){
        Course course=Course.builder()
                .courseName(courseRequestDto.getCourseName())
                .courseCode(courseRequestDto.getCourseCode())
                .courseDescription(courseRequestDto.getCourseDescription())
                .courseCredit(courseRequestDto.getCourseCredit())
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

    public CourseResponseDto updateCourse(Long courseid, CourseRequestDto courseRequestDto){
        Course course=courseRepo.findById(courseid).orElseThrow(()-> new RuntimeException("course with id"+courseid+"not found"));
        course.setCourseName(courseRequestDto.getCourseName());
        course.setCourseCode(courseRequestDto.getCourseCode());
        course.setCourseDescription(courseRequestDto.getCourseDescription());
        course.setCourseCredit(courseRequestDto.getCourseCredit());


        CourseResponseDto coursedto= findCourseById(courseid);
        coursedto.setId(course.getId());
        coursedto.setCourseName(course.getCourseName());
        coursedto.setCourseCode(course.getCourseCode());
        coursedto.setCourseDescription(course.getCourseDescription());
        coursedto.setCourseCredit(course.getCourseCredit());
        courseRepo.save(course);
        return coursedto;
    }


}
