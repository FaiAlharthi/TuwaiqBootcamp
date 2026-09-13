package com.example.lab7.Service;

import com.example.lab7.Model.Course;
import com.example.lab7.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    public ArrayList<Student> students = new ArrayList<>();

    //READ Method
    public ArrayList<Student> getAllStudents(){
        return students;
    }

    //CREATE Method
    public void addStudent(Student student){
        students.add(student);
    }

    //UPDATE Method
    public boolean updateStudent(int index, Student student){
        if(index >= students.size() || index < 0){
            return false;
        }
        students.set(index, student);
        return true;
    }

    //DELETE Method
    public boolean deleteStudent(int index){
        if(index >= students.size() || index < 0){
            return false;
        }
        students.remove(index);
        return true;
    }

    //list all courses information that a student study
    public ArrayList<Course> listCourses ( String studentID){
        for(Student student : students){
            if(student.getStudentID().equalsIgnoreCase(studentID)){
                return student.getStudentCourses();
            }
        }
        return null;
    }

    //calculate students GPA
    public boolean addNewCourse(int index,Course course){
        if(index >= students.size() || index < 0){
            return false;
        }
        students.get(index).addCourse(course);
        return true;
    }

    //determine the honor class
    public String honorClasses(int index){
        if(students.get(index).getGPA()>=4.75){
            return "First Honor Class";
        }else if(students.get(index).getGPA()>=4.25){
            return "Second Honor Class";
        }
        return "No Honor Classes";
    }

    //add new grade
    public boolean addGrade(String studentID, String CourseCode, double finalGrade){
        for(Student student : students){
            if(student.getStudentID().equalsIgnoreCase(studentID)){
                for(Course course: student.getStudentCourses()){
                    if(course.getCode().equalsIgnoreCase(CourseCode)){
                        course.setFinalGrade(finalGrade);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
