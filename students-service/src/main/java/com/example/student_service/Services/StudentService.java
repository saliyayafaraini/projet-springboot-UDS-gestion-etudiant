package com.example.student_service.Services;

import com.example.student_service.Entity.Student;

import java.util.List;

public interface StudentService {
    Student save(Student studentDto);

    List<Student> findAllPage(int pageNum, int pageSize);

    Student findStudentById(Long studentId);
}
