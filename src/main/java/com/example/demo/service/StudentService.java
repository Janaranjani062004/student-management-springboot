package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // ================= CREATE =================
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // ================= READ ALL WITH PAGINATION =================
    public Page<Student> getAllStudents(int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("id").descending()
        );

        return studentRepository.findAll(pageable);
    }

    // ================= SEARCH BY NAME =================
    public Page<Student> searchStudentsByName(String name, int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("id").descending()
        );

        return studentRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    // ================= READ BY ID =================
    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));
    }

    // ================= UPDATE =================
    public Student updateStudent(int id, Student studentDetails) {

        Student student = getStudentById(id);

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setAge(studentDetails.getAge());

        return studentRepository.save(student);
    }

    // ================= DELETE =================
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}


