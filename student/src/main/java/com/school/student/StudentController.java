package com.school.student;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody Student student
    ){
        studentService.saveStudent(student);
    }
    @GetMapping
    public ResponseEntity<List<Student>> findAllStudent(){
        return ResponseEntity.ok(studentService.findAllStudents());
    }
    @GetMapping("/school/{school-id}")
    public ResponseEntity<List<Student>> findAllStudent(
            @PathVariable("school-id") Integer schoolId
    ){
        return ResponseEntity.ok(studentService.findAllBySchoolId(schoolId));
    }
}
