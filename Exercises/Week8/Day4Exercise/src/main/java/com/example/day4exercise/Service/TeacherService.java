package com.example.day4exercise.Service;

import com.example.day4exercise.Api.ApiException;
import com.example.day4exercise.Model.Teacher;
import com.example.day4exercise.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    //CRUD

    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    public void createTeacher(Teacher teacher){
        Teacher teacher1 = teacherRepository.findTeacherById(teacher.getId());
        if(teacher1 == null){
            throw new ApiException("no customer found");
        }
        teacherRepository.save(teacher1);
    }

    public void updateTeacher( Integer id,Teacher teacher){
        Teacher teacher1 = teacherRepository.findTeacherById(id);
        if(teacher1 == null){
            throw new ApiException("no customer found");
        }
        teacher1.setName(teacher.getName());
        teacher1.setEmail(teacher.getEmail());
        teacher1.setAge(teacher.getAge());
        teacher1.setSalary(teacher.getSalary());

        teacherRepository.save(teacher1);
    }

    public void deleteTeacher(Integer id){
        Teacher teacher = teacherRepository.findTeacherById(id);
        if(teacher == null){
            throw new ApiException("no customer found");
        }
        teacherRepository.delete(teacher);
    }
}
