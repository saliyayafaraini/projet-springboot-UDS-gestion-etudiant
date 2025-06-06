package com.example.course_service.Controllers;

import com.example.course_service.Entity.Course;
import com.example.course_service.Entity.StudentFollowCourse;
import com.example.course_service.Services.CourseService;
import com.example.course_service.Services.StudentFollowCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/student_follow_course")
@CrossOrigin
public class StudentFollowCourseController {

    final StudentFollowCourseService studentFollowCourseService;
    final CourseService courseService;
    private final WebClient.Builder webClientBuilder;

    public StudentFollowCourseController(StudentFollowCourseService studentFollowCourseService, CourseService courseService, WebClient.Builder webClientBuilder) {
        this.studentFollowCourseService = studentFollowCourseService;
        this.courseService = courseService;
        this.webClientBuilder = webClientBuilder;
    }

    @PostMapping("/addStudentFollowCourse")
    public ResponseEntity<?> addStudentFollowCourse(@RequestBody StudentFollowCourse studentFollowCourseDto) {

        // check whether student exists --- localhost:8080
        Boolean studentExists = webClientBuilder.build().get()
                .uri("http://students-service/student/doesStudentExist/" + studentFollowCourseDto.getStudentId())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.TRUE.equals(studentExists)) {
            Course course = courseService.findCourseById(studentFollowCourseDto.getCourseId());

            boolean courseExists = course != null;

            if (Boolean.TRUE.equals(courseExists)) {
                studentFollowCourseDto.setCreatedAt(new Date());
                StudentFollowCourse studentFollowCourse = studentFollowCourseService.save(studentFollowCourseDto);
                return new ResponseEntity<>(studentFollowCourse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Course does not exist", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Student does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCoursesFollowedByStudent")
    public ResponseEntity<?> getCoursesFollowedByStudent(
            @RequestParam("studentId") Long studentId,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize
    ) {

        List<StudentFollowCourse> studentFollowCourseList = studentFollowCourseService.findAllByStudentIdPage(studentId, pageNum, pageSize);

        List<Long> coursesIds = new ArrayList<>();
        for (StudentFollowCourse studentFollowCourse : studentFollowCourseList) {
            coursesIds.add(studentFollowCourse.getCourseId());
        }
        List<Course> courseList = courseService.findAllByCourseIds(coursesIds);

        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }
}
