package com.example.student_service.Controllers;

import com.example.student_service.Entity.Student;
import com.example.student_service.Services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@RequestBody Student studentDto){
        studentDto.setCreatedAt(new Date());
        Student student = studentService.save(studentDto);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/getStudents")
    public ResponseEntity<?> getStudentsPage(
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize
    ){
        // language (en; fr)
        List<Student> studentList = studentService.findAllPage(pageNum, pageSize);

        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/doesStudentExist/{studentId}")
    public boolean doesStudentExist(@PathVariable Long studentId){
        Student student = studentService.findStudentById(studentId);

        return student != null;
    }

}
