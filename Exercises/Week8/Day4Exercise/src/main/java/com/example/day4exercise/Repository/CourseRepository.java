package com.example.day4exercise.Repository;

import com.example.day4exercise.Model.Course;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findCourseById(Integer id);
}
