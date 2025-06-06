package com.example.course_service.Services;

import com.example.course_service.Entity.TeacherTeachesCourse;

import java.util.List;

public interface TeacherTeachesCourseService {
    TeacherTeachesCourse save(TeacherTeachesCourse teacherTeachesCourseDto);

    List<TeacherTeachesCourse> findAllByTeacherIdPage(Long teacherId, int pageNum, int pageSize);
}
