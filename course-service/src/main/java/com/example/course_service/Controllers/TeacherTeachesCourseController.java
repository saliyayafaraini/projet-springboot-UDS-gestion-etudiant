package com.example.course_service.Controllers;

import com.example.course_service.Entity.Course;
import com.example.course_service.Entity.TeacherTeachesCourse;
import com.example.course_service.Services.CourseService;
import com.example.course_service.Services.TeacherTeachesCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/teacher_teaches_course_controller")
@CrossOrigin
public class TeacherTeachesCourseController {

    final TeacherTeachesCourseService teacherTeachesCourseService;
    final CourseService courseService;
    private final WebClient.Builder webClientBuilder;

    public TeacherTeachesCourseController(TeacherTeachesCourseService teacherTeachesCourseService, CourseService courseService, WebClient.Builder webClientBuilder) {
        this.teacherTeachesCourseService = teacherTeachesCourseService;
        this.courseService = courseService;
        this.webClientBuilder = webClientBuilder;
    }

    @PostMapping("/addTeacherTeachesCourse")
    public ResponseEntity<?> addTeacherTeachesCourse(@RequestBody TeacherTeachesCourse teacherTeachesCourseDto) {

        // check whether teacher exists     -- localhost:8081
        Boolean teacherExists = webClientBuilder.build().get()
                .uri("http://teachers-service/teacher/doesTeacherExist/" + teacherTeachesCourseDto.getTeacherId())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.TRUE.equals(teacherExists)) {
            Course course = courseService.findCourseById(teacherTeachesCourseDto.getCourseId());

            boolean courseExists = course != null;

            if (Boolean.TRUE.equals(courseExists)) {
                teacherTeachesCourseDto.setCreatedAt(new Date());
                TeacherTeachesCourse teacherTeachesCourse = teacherTeachesCourseService.save(teacherTeachesCourseDto);
                return new ResponseEntity<>(teacherTeachesCourse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Course does not exist", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Teacher does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCoursesTeachByTeacher")
    public ResponseEntity<?> getCoursesTeachByTeacher(
            @RequestParam("teacherId") Long teacherId,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize
    ) {

        List<TeacherTeachesCourse> teacherTeachesCourseList = teacherTeachesCourseService.findAllByTeacherIdPage(teacherId, pageNum, pageSize);

        List<Long> coursesIds = new ArrayList<>();
        for (TeacherTeachesCourse teacherTeachesCourse : teacherTeachesCourseList) {
            coursesIds.add(teacherTeachesCourse.getCourseId());
        }
        List<Course> courseList = courseService.findAllByCourseIds(coursesIds);

        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }
}
