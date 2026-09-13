package com.example.exam2.Service;

import com.example.exam2.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    ArrayList<Student> students = new ArrayList<>();

    //Get all students
    public ArrayList<Student> getAllStudents(){
        return students;
    }

    //Add new student
    public boolean addNewStudent(Student student){
        students.add(student);
        return true;
    }

    //update student
    public boolean updateStudent(int index, Student student){
        if(index >=students.size() || index < 0){
            return false;
        }
        students.set(index, student);
        return true;
    }

    //delete student
    public boolean deleteStudent(int index){
        if(index >=students.size() || index < 0){
            return false;
        }
        students.remove(index);
        return true;
    }

    //takes a student name and returns one student
    public Student returnStudentByName (String name){
        for(Student student: students){
            if(student.getName().equalsIgnoreCase(name)){
                return student;
            }
        }
        return null;
    }

    //takes a major and returns all students who have this major.
    public ArrayList<Student> returnMajorStudent (String major){
        ArrayList<Student> majorStudents = new ArrayList<>();
        for(Student student : students){
            if(student.getMajor().equalsIgnoreCase(major)){
                majorStudents.add(student);
            }
        }
        return majorStudents;
    }

}
