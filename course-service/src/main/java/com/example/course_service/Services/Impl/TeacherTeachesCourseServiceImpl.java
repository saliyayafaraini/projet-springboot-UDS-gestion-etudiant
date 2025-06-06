package com.example.course_service.Services.Impl;

import com.example.course_service.Entity.TeacherTeachesCourse;
import com.example.course_service.Repository.TeacherTeachesCourseRepository;
import com.example.course_service.Services.TeacherTeachesCourseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherTeachesCourseServiceImpl implements TeacherTeachesCourseService {
    final TeacherTeachesCourseRepository teacherTeachesCourseRepository;

    public TeacherTeachesCourseServiceImpl(TeacherTeachesCourseRepository teacherTeachesCourseRepository) {
        this.teacherTeachesCourseRepository = teacherTeachesCourseRepository;
    }

    @Override
    public TeacherTeachesCourse save(TeacherTeachesCourse teacherTeachesCourseDto) {
        return teacherTeachesCourseRepository.save(teacherTeachesCourseDto);
    }

    @Override
    public List<TeacherTeachesCourse> findAllByTeacherIdPage(Long teacherId, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        return teacherTeachesCourseRepository.findAllByTeacherId(teacherId, pageable);
    }
}
