package com.example.course_service.Controllers;

import com.example.course_service.Entity.Course;
import com.example.course_service.Services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {
    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/addCourse")
    public ResponseEntity<?> addCourse(@RequestBody Course courseDto){
        courseDto.setCreatedAt(new Date());
        Course course = courseService.save(courseDto);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/getCourses")
    public ResponseEntity<?> getCoursesPage(
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize
    ){

        List<Course> courseList = courseService.findAllPage(pageNum, pageSize);

        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/doesCourseExist/{courseId}")
    public boolean doesCourseExist(@PathVariable Long courseId){
        Course course = courseService.findCourseById(courseId);

        return course != null;
    }
}
