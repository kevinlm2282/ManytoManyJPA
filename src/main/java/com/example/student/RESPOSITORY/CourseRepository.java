package com.example.student.RESPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.ENTITY.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{

}
