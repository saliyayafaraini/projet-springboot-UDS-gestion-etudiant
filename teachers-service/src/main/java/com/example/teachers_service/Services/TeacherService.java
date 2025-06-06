package com.example.teachers_service.Services;

import com.example.teachers_service.Entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher save(Teacher teacherDto);

    List<Teacher> findAllPage(int pageNum, int pageSize);

    Teacher findTeacherById(Long teacherId);
}
