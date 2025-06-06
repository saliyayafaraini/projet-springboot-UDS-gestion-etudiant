package com.example.course_service.Repository;

import com.example.course_service.Entity.TeacherTeachesCourse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherTeachesCourseRepository extends JpaRepository<TeacherTeachesCourse, Long> {

    List<TeacherTeachesCourse> findAllByTeacherId(Long teacherId, Pageable pageable);
}
