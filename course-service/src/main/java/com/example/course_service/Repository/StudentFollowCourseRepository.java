package com.example.course_service.Repository;

import com.example.course_service.Entity.StudentFollowCourse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentFollowCourseRepository extends JpaRepository<StudentFollowCourse, Long> {

    List<StudentFollowCourse> findAllByStudentId(Long studentId, Pageable pageable);
}
