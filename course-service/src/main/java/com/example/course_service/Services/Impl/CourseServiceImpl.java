package com.example.course_service.Services.Impl;

import com.example.course_service.Entity.Course;
import com.example.course_service.Repository.CourseRepository;
import com.example.course_service.Services.CourseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAllPage(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        return courseRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Course> findAllByCourseIds(List<Long> coursesIds) {
        return courseRepository.findAllById(coursesIds);
    }

    @Override
    public Course findCourseById(Long courseId) {
        return courseRepository.findById( courseId).orElse(null);
    }
}
