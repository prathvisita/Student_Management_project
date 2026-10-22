package com.pk.Student_Management_project.Service;

import com.pk.Student_Management_project.DTO.StudentDto;
import com.pk.Student_Management_project.Entity.Course;
import com.pk.Student_Management_project.Entity.Student;
import com.pk.Student_Management_project.Repository.CourseRepo;
import com.pk.Student_Management_project.Repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    public List<StudentDto> getAllStudents() {
        List<Student>students = studentRepo.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for(Student student : students){
            StudentDto studentDto=StudentDto.builder()
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .email(student.getEmail())
                    .mobileNumber(student.getMobileNumber())
                    .build();
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }

    public StudentDto findById(Long studentid){
        Student student = studentRepo.findById(studentid).orElseThrow(()-> new RuntimeException("Student not found with id"+studentid));
        StudentDto studentDto=StudentDto.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .mobileNumber(student.getMobileNumber())
                .build();
        return studentDto;
    }

    public void createStudent(StudentDto studentDto){
        Student student=Student.builder()
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .email(studentDto.getEmail())
                .mobileNumber(studentDto.getMobileNumber())
                .build();
        studentRepo.save(student);
    }

    public StudentDto updateStudent(Long id,StudentDto studentDto){
        Student student=studentRepo.findById(id).orElseThrow(()-> new RuntimeException("Student not found with id"+id));
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setMobileNumber(studentDto.getMobileNumber());
        studentRepo.save(student);

        StudentDto studentdto=findById(id);
        studentdto.setFirstName(studentDto.getFirstName());
        studentdto.setLastName(studentDto.getLastName());
        studentdto.setEmail(studentDto.getEmail());
        studentdto.setMobileNumber(studentDto.getMobileNumber());
        return studentdto;
    }
    public void deleteStudent(Long id){
        if(studentRepo.findById(id).isPresent()){
            studentRepo.deleteById(id);
        }else{
            throw new RuntimeException("course with id"+id+" not found");
        }
    }

    public StudentDto enrollToCourse(Long studentid,Long courseid){
        Course course=courseRepo.findById(courseid).orElseThrow(()-> new RuntimeException("course not found with id"+courseid));
        Student student=studentRepo.findById(studentid).orElseThrow(()-> new RuntimeException("student not found with id"+studentid));

        student.getCourseList().add(course);

        StudentDto studentdto=findById(studentid);
        studentRepo.save(student);
        return studentdto;
    }

    public StudentDto unenrollToCourse(Long studentid,Long courseid){
        Course course=courseRepo.findById(courseid).orElseThrow(()-> new RuntimeException("course not found with id"+courseid));
        Student student=studentRepo.findById(studentid).orElseThrow(()-> new RuntimeException("student not found with id"+studentid));

        student.getCourseList().remove(course);

        StudentDto studentdto=findById(studentid);
        studentRepo.save(student);
        return studentdto;
    }
}
