package com.pk.Student_Management_project.Students;

import com.pk.Student_Management_project.Courses.Course;
import com.pk.Student_Management_project.Courses.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    @Transactional
    public List<StudentResponseDto> getAllStudents() {
        List<Student>students = studentRepo.findAll();
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();
        for(Student student : students){
            StudentResponseDto studentResponseDto = StudentResponseDto.builder()
                    .id(student.getId())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .email(student.getEmail())
                    .mobileNumber(student.getMobileNumber())
                   // .courses(student.getCourseList())
                    .build();
            studentResponseDtos.add(studentResponseDto);
        }
        return studentResponseDtos;
    }

    public StudentResponseDto findById(Long studentid){
        Student student = studentRepo.findById(studentid).orElseThrow(()-> new RuntimeException("Student not found with id"+studentid));
        StudentResponseDto studentResponseDto = StudentResponseDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .mobileNumber(student.getMobileNumber())
                //.courses(student.getCourseList())
                .build();
        return studentResponseDto;
    }

    @Transactional
    public void createStudent(StudentRequestDto studentRequestDto){
        Student student=Student.builder()
                .firstName(studentRequestDto.getFirstName())
                .lastName(studentRequestDto.getLastName())
                .email(studentRequestDto.getEmail())
                .mobileNumber(studentRequestDto.getMobileNumber())
                .build();
        studentRepo.save(student);
    }

    @Transactional
    public StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDto){
        Student student=studentRepo.findById(id).orElseThrow(()-> new RuntimeException("Student not found with id"+id));
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setEmail(studentRequestDto.getEmail());
        student.setMobileNumber(studentRequestDto.getMobileNumber());
        studentRepo.save(student);

        StudentResponseDto studentdto=StudentResponseDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .mobileNumber(student.getMobileNumber())
                .build();
        return studentdto;
    }

    @Transactional
    public void deleteStudent(Long id){
        if(studentRepo.findById(id).isPresent()){
            Student student = studentRepo.findById(id).orElseThrow(()-> new RuntimeException("Student not found with id"+id));
            studentRepo.delete(student);
        }else{
            throw new RuntimeException("course with id"+id+" not found");
        }
    }

    @Transactional
    public StudentResponseDto enrollToCourse(Long studentid, Long courseid){
        Course course=courseRepo.findById(courseid).orElseThrow(()-> new RuntimeException("course not found with id"+courseid));
        Student student=studentRepo.findById(studentid).orElseThrow(()-> new RuntimeException("student not found with id"+studentid));

        student.getCourseList().add(course);

        StudentResponseDto studentdto=findById(studentid);
        studentRepo.save(student);
        return studentdto;
    }

    @Transactional
    public StudentResponseDto unenrollToCourse(Long studentid, Long courseid){
        Course course=courseRepo.findById(courseid).orElseThrow(()-> new RuntimeException("course not found with id"+courseid));
        Student student=studentRepo.findById(studentid).orElseThrow(()-> new RuntimeException("student not found with id"+studentid));

        student.getCourseList().remove(course);

        StudentResponseDto studentdto=findById(studentid);
        studentRepo.save(student);
        return studentdto;
    }
}
