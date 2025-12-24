package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // ================= CREATE =================
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // ================= READ (ALL) =================
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // ================= READ (BY ID) =================
    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));
    }

    // ================= UPDATE =================
    public Student updateStudent(int id, Student studentDetails) {

        Student existingStudent = getStudentById(id);

        existingStudent.setName(studentDetails.getName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setAge(studentDetails.getAge());

        return studentRepository.save(existingStudent);
    }

    // ================= DELETE =================
    public void deleteStudent(int id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    // ================= PAGINATION =================
    public Page<Student> getStudentsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    // ================= SEARCH WITH PAGINATION =================
    public Page<Student> searchStudentsByName(String name, Pageable pageable) {
        return studentRepository.findByNameContainingIgnoreCase(name, pageable);
    }
}



