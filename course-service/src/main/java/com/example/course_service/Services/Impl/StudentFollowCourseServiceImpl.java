package com.example.course_service.Services.Impl;

import com.example.course_service.Entity.StudentFollowCourse;
import com.example.course_service.Repository.StudentFollowCourseRepository;
import com.example.course_service.Services.StudentFollowCourseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentFollowCourseServiceImpl implements StudentFollowCourseService {
    final StudentFollowCourseRepository studentFollowCourseRepository;

    public StudentFollowCourseServiceImpl(StudentFollowCourseRepository studentFollowCourseRepository) {
        this.studentFollowCourseRepository = studentFollowCourseRepository;
    }

    @Override
    public StudentFollowCourse save(StudentFollowCourse studentFollowCourseDto) {
        return studentFollowCourseRepository.save(studentFollowCourseDto);
    }

    @Override
    public List<StudentFollowCourse> findAllByStudentIdPage(Long studentId, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        return studentFollowCourseRepository.findAllByStudentId(studentId, pageable);
    }
}
