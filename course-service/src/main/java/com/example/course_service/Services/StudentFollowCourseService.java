package com.example.course_service.Services;

import com.example.course_service.Entity.StudentFollowCourse;

import java.util.List;

public interface StudentFollowCourseService {
    StudentFollowCourse save(StudentFollowCourse studentFollowCourseDto);

    List<StudentFollowCourse> findAllByStudentIdPage(Long studentId, int pageNum, int pageSize);
}
