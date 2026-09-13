package com.example.lab7.Service;

import com.example.lab7.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {
    ArrayList<Course> courses = new ArrayList<>();

    //READ Method
    public ArrayList<Course> getAllCourses(){
        return courses;
    }

    //CREATE Methods
    public boolean createCourse(Course course){
        courses.add(course);
        return true;
    }

    //Update Methods
    public boolean updateCourse(int index, Course course){
        if(index >= courses.size() || index < 0){
            return false;
        }
        courses.set(index,course);
        return true;
    }

    //Delete Method
    public boolean deleteCourse(int index){
        if(index >= courses.size() || index < 0){
            return false;
        }
        courses.remove(index);
        return true;
    }

    //add an announcement
    public boolean addAnnouncement( int index,String announcement){
        if(index >= courses.size() || index < 0){
            return false;
        }
        courses.get(index).addAnnouncement(announcement);
        return true;
    }

    //get the instructor name
    public String getInstructor(String courseInfo){
        for (Course course : courses){
            if(course.getCode().equalsIgnoreCase(courseInfo) || course.getName().equalsIgnoreCase(courseInfo) ){
                return course.getInstructorName();
            }
        }
        return null;
    }

    //get number of required exams
    public int getExams ( int index){
        if(index >= courses.size() || index < 0){
            return -1;
        }
        return courses.get(index).getNumberOfRequiredExams();
    }

    //update number of required exams
    public boolean updateExams ( int index, int newNoExams){
        if(index >= courses.size() || index < 0){
            return false;
        }
        if(newNoExams < 2){
            return false;
        }
        courses.get(index).setNumberOfRequiredExams(newNoExams);
        return true;
    }


}
