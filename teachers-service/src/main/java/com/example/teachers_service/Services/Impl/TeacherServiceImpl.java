package com.example.teachers_service.Services.Impl;

import com.example.teachers_service.Entity.Teacher;
import com.example.teachers_service.Repository.TeacherRepository;
import com.example.teachers_service.Services.TeacherService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> findAllPage(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("firstName").ascending());

        return teacherRepository.findAll(pageable).getContent();
    }

    @Override
    public Teacher findTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElse(null);
    }
}
