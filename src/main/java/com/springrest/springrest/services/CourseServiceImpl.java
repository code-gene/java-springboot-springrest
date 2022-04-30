package com.springrest.springrest.services;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{
    private CourseDao courseDao;

    public CourseServiceImpl() {
    }

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> getCourses() {
        return courseDao.findAll();
    }

    @Override
    public Course getCourse(Long courseId) {
        if (!courseDao.existsById(courseId)) {
            throw new IllegalStateException("course with id " + courseId + " does not exists");
        }
        return courseDao.getById(courseId);
    }

    @Override
    public Course addCourse(Course course) {
        courseDao.save(course);
        return course;
    }

    @Override
    public Course updateCourse(Course course) {
        Course c = courseDao.findById(course.getId()).orElseThrow(() -> new IllegalStateException("course with id " + course.getId() + " does not exists"));

        courseDao.save(course);
        return course;
    }

    @Override
    public void deleteCourse(long courseId) {
        if (!courseDao.existsById(courseId)) {
            throw new IllegalStateException("course with id " + courseId + " does not exists");
        }
        Course course=courseDao.getById(courseId);
        courseDao.delete(course);
    }
}
