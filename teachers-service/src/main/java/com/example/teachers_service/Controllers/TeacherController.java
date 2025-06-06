package com.example.teachers_service.Controllers;

import com.example.teachers_service.Entity.Teacher;
import com.example.teachers_service.Services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {
    final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/addTeacher")
    public ResponseEntity<?> addTeacher(@RequestBody Teacher teacherDto){
        teacherDto.setCreatedAt(new Date());
        Teacher teacher = teacherService.save(teacherDto);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("/getTeachers")
    public ResponseEntity<?> getTeachersPage(
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize
    ){

        List<Teacher> teacherList = teacherService.findAllPage(pageNum, pageSize);

        return new ResponseEntity<>(teacherList, HttpStatus.OK);
    }

    @GetMapping("/doesTeacherExist/{teacherId}")
    public boolean doesTeacherExist(@PathVariable Long teacherId){
        Teacher teacher = teacherService.findTeacherById(teacherId);

        return teacher != null;
    }
}
