package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ================= CREATE STUDENT =================
    @PostMapping
    public ResponseEntity<StudentDTO> saveStudent(
            @Valid @RequestBody StudentDTO studentDTO) {

        StudentDTO savedStudent = studentService.saveStudent(studentDTO);
        return ResponseEntity.ok(savedStudent);
    }

    // ================= READ ALL + SEARCH + PAGINATION =================
    @GetMapping
    public ResponseEntity<Page<StudentDTO>> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String search) {

        Page<StudentDTO> students;

        if (search == null || search.trim().isEmpty()) {
            students = studentService.getAllStudents(page, size);
        } else {
            students = studentService.searchStudentsByName(search, page, size);
        }

        return ResponseEntity.ok(students);
    }

    // ================= READ BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id) {
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // ================= UPDATE STUDENT =================
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable int id,
            @Valid @RequestBody StudentDTO studentDTO) {

        StudentDTO updatedStudent =
                studentService.updateStudent(id, studentDTO);

        return ResponseEntity.ok(updatedStudent);
    }

    // ================= DELETE STUDENT =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStudent(
            @PathVariable int id) {

        studentService.deleteStudent(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}






