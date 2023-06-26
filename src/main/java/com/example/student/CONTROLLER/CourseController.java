package com.example.student.CONTROLLER;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.ENTITY.Course;
import com.example.student.ENTITY.Student;
import com.example.student.ENTITY.StudentCourse;
import com.example.student.RESPOSITORY.CourseRepository;
import com.example.student.RESPOSITORY.StudentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/course")
@CrossOrigin("*")
@Service
public class CourseController {

    Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
    
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;


    @GetMapping(value="/")
    public List<Course> getMethodName() {
        return this.courseRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping(value="/")
    public Course postMethodName(@RequestBody Course course) {
        LOGGER.info("Los datos de entrada son: {}", course.toString());
        Course getCourse = new Course();
        getCourse.setName(course.getName());
        getCourse.setAmount(course.getAmount());
        getCourse.setRatings(course.getRatings().stream().map(student ->{
            Student getStudent = studentRepository.findById(student.getStudent().getId()).orElseThrow();
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setStudent(getStudent);
            studentCourse.setCourse(getCourse);
            studentCourse.setRating(student.getRating());
            return studentCourse;
        }).collect(Collectors.toSet()));
        Course saveCourse = courseRepository.save(getCourse);
        getCourse.setId(saveCourse.getId());


        // Set<StudentCourse> studentCourses = new HashSet<>();
        // for (StudentCourse studentCourse : course.getRatings()) {
        //     LOGGER.info("STUDEN-COURSE: {}",studentCourse.toString());
        //     StudentCourse studentCourse2 = new StudentCourse();
        //     studentCourse2.setStudent(studentCourse.getStudent());
        //     studentCourse2.setCourse(course);
        //     studentCourses.add(studentCourse2);
        // }
        // course.setRatings(studentCourses);
        // Course saveCourse = courseRepository.save(course);
        // LOGGER.info("SAVE-COURSE: {}", saveCourse);
        // course.setId(saveCourse.getId());
        return saveCourse;
    }
    
    
    


    
}
