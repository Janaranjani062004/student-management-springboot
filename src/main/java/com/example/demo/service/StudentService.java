package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // ================= CREATE =================
    public StudentDTO saveStudent(StudentDTO dto) {
        Student student = mapToEntity(dto);
        Student savedStudent = studentRepository.save(student);
        return mapToDTO(savedStudent);
    }

    // ================= READ ALL WITH PAGINATION =================
    public Page<StudentDTO> getAllStudents(int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("id").descending()
        );

        Page<Student> studentPage = studentRepository.findAll(pageable);

        List<StudentDTO> dtoList = studentPage.getContent()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, studentPage.getTotalElements());
    }

    // ================= SEARCH BY NAME =================
    public Page<StudentDTO> searchStudentsByName(String name, int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("id").descending()
        );

        Page<Student> studentPage =
                studentRepository.findByNameContainingIgnoreCase(name, pageable);

        List<StudentDTO> dtoList = studentPage.getContent()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, studentPage.getTotalElements());
    }

    // ================= READ BY ID =================
    public StudentDTO getStudentById(int id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id: " + id));

        return mapToDTO(student);
    }

    // ================= UPDATE =================
    public StudentDTO updateStudent(int id, StudentDTO dto) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id: " + id));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());

        Student updatedStudent = studentRepository.save(student);
        return mapToDTO(updatedStudent);
    }

    // ================= DELETE =================
    public void deleteStudent(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id: " + id));
        studentRepository.delete(student);
    }

    // ================= MAPPING METHODS =================
    private StudentDTO mapToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setAge(student.getAge());
        return dto;
    }

    private Student mapToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        return student;
    }
}
