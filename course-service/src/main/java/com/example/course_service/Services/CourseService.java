package com.example.course_service.Services;


import com.example.course_service.Entity.Course;

import java.util.List;

public interface CourseService {
    Course save(Course courseDto);

    List<Course> findAllPage(int pageNum, int pageSize);

    List<Course> findAllByCourseIds(List<Long> coursesIds);

    Course findCourseById(Long courseId);
}
