package com.example.day4exercise.Service;

import com.example.day4exercise.Api.ApiException;
import com.example.day4exercise.DTO.TeacherDTO;
import com.example.day4exercise.Model.Course;
import com.example.day4exercise.Model.Student;
import com.example.day4exercise.Model.Teacher;
import com.example.day4exercise.Repository.CourseRepository;
import com.example.day4exercise.Repository.StudentRepository;
import com.example.day4exercise.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public void createCourse(Course course){
        Course course1 = courseRepository.findCourseById(course.getId());
        if(course1 !=null){
            throw new ApiException("course with this id already exist");
        }
        courseRepository.save(course);
    }

    public void assignCourseToStudent(Integer course_id, Integer student_id){
        Course course = courseRepository.findCourseById(course_id);
        Student student = studentRepository.findStudentById(student_id);
        if(course == null || student == null){
            throw new ApiException("course or student with this id not exist");
        }
        course.getStudents().add(student);
        student.getCourses().add(course);

        courseRepository.save(course);
        studentRepository.save(student);
    }

    public void updateCourse(Integer course_id, Course course ){
        Course course1 = courseRepository.findCourseById(course_id);
        if(course1 !=null){
            throw new ApiException("course with this id not exist");
        }
        course1.setName(course.getName());
        courseRepository.save(course1);
    }

    public void deleteCourse (Integer id){
        Course course = courseRepository.findCourseById(id);
        if(course !=null){
            throw new ApiException("course with this id not exist");
        }
        courseRepository.delete(course);
    }

    public TeacherDTO teacherOfCourse(Integer course_id){
        Course course = courseRepository.findCourseById(course_id);
        TeacherDTO teacherDTO = new TeacherDTO(course.getTeacher().getName());
        return teacherDTO;
    }
}
