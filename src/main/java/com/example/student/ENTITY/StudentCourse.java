package com.example.student.ENTITY;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity(name = "student_course")
@Table(name = "student_course", schema = "jpa2")
public class StudentCourse {
    
    @EmbeddedId
    private StudentCourseKey student_course_key =  new StudentCourseKey();

    @ManyToOne(optional = false)
    @MapsId("studentId")
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne(optional = false)
    @MapsId("courseId")
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    @JsonIgnore
    private Course course;

    @Column(name = "rating")
    private Integer rating;

    public StudentCourse() {
    }

    public StudentCourse(StudentCourseKey student_course_key, Student student, Course course, Integer rating) {
        this.student_course_key = student_course_key;
        this.student = student;
        this.course = course;
        this.rating = rating;
    }

    public StudentCourseKey getStudent_course_key() {
        return student_course_key;
    }

    public void setStudent_course_key(StudentCourseKey student_course_key) {
        this.student_course_key = student_course_key;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((student_course_key == null) ? 0 : student_course_key.hashCode());
        result = prime * result + ((student == null) ? 0 : student.hashCode());
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((rating == null) ? 0 : rating.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentCourse other = (StudentCourse) obj;
        if (student_course_key == null) {
            if (other.student_course_key != null)
                return false;
        } else if (!student_course_key.equals(other.student_course_key))
            return false;
        if (student == null) {
            if (other.student != null)
                return false;
        } else if (!student.equals(other.student))
            return false;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (rating == null) {
            if (other.rating != null)
                return false;
        } else if (!rating.equals(other.rating))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "StudentCourse [student_course_key=" + student_course_key + ", student=" + student + ", course=" + course
                + ", rating=" + rating + "]";
    }

    
    

}
