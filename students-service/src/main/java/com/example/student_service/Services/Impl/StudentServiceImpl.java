package com.example.student_service.Services.Impl;

import com.example.student_service.Entity.Student;
import com.example.student_service.Repository.StudentRepository;
import com.example.student_service.Services.StudentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student studentDto) {
        return studentRepository.save(studentDto);
    }

    @Override
    public List<Student> findAllPage(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("firstName").ascending());

        return studentRepository.findAll(pageable).getContent();
    }

    @Override
    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }
}
