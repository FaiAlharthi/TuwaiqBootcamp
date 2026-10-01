package com.example.day4exercise.Service;

import com.example.day4exercise.Api.ApiException;
import com.example.day4exercise.Model.Student;
import com.example.day4exercise.Model.Teacher;
import com.example.day4exercise.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void createStudent(Student student){
        Student student1 = studentRepository.findStudentById(student.getId());
        if(student1 != null){
            throw new ApiException("no student found");
        }
        studentRepository.save(student1);
    }

    public void updateStudent( Integer id,Student student){
        Student student1 = studentRepository.findStudentById(id);
        if(student1 == null){
            throw new ApiException("no student found");
        }
        student1.setName(student.getName());
        student1.setAge(student.getAge());
        student1.setMajor(student.getMajor());
        studentRepository.save(student1);
    }

    public void deleteStudent(Integer id){
        Student student = studentRepository.findStudentById(id);
        if(student == null){
            throw new ApiException("no student found");
        }
        studentRepository.delete(student);
    }

}
