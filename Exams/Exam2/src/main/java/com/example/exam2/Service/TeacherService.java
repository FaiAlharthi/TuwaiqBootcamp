package com.example.exam2.Service;

import com.example.exam2.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {
    ArrayList<Teacher> teachers = new ArrayList<>();

    //Get all teachers
    public ArrayList<Teacher> getAllTeachers (){
        return teachers;
    }

    //Add new teacher
    public boolean addNewTeacher(Teacher teacher){
        teachers.add(teacher);
        return true;
    }

    //update teacher
    public boolean updateTeacher(int index, Teacher teacher){
        if(index >= teachers.size() || index < 0){
            return false;
        }
        teachers.set(index, teacher);
        return true;
    }

    //delete teacher
    public boolean deleteTeacher(int index){
        if(index >= teachers.size() || index < 0){
            return false;
        }
        teachers.remove(index);
        return true;
    }

    // takes a teacher id and returns one teacher
    public Teacher TeacherByID(String id){
        for(Teacher teacher : teachers){
            if(teacher.getID().equalsIgnoreCase(id)){
                return teacher;
            }
        }
        return null;
    }

    //takes a salary and return all teachers who have this salary or above
    public ArrayList<Teacher> TeachersBySalary(double salary){
        ArrayList<Teacher> higherSalaries = new ArrayList<>();
        for(Teacher teacher : teachers){
            if( teacher.getSalary() >= salary){
                higherSalaries.add(teacher);
            }
        }
        return higherSalaries;
    }
}
