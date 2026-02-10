package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    //  Search students by name (case-insensitive, partial match) with pagination
    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);
}


