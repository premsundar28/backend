package com.example.student3.controller;

import com.example.student3.model.Faculty;
import com.example.student3.service.JwtService;
import com.example.student3.Entity.UserInfo;
import com.example.student3.model.AuthRequest;
import com.example.student3.model.Student;
import com.example.student3.repository.StudentRepository;
import com.example.student3.service.StudentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private StudentQuery service;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/Student/addStudent")
    @PreAuthorize("hasAuthority('admin')")
    public void addStudent(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @GetMapping("/Student/showAllStudents")
    @PreAuthorize("hasAuthority('admin')")
    public List<Student> showAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/Student/updateStudent")
    @PreAuthorize("hasAuthority('admin')")
    public void updateStudent(@RequestBody Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(updatedStudent.getStudentId());
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setStudentName(updatedStudent.getStudentName());
            existingStudent.setStudentMobileNumber(updatedStudent.getStudentMobileNumber());
            studentRepository.save(existingStudent);
        }
    }

    @DeleteMapping("/Student/deleteStudent/{studentId}")
    @PreAuthorize("hasAuthority('admin')")
    public void deleteStudentById(@PathVariable("studentId") String studentId) {
        studentRepository.deleteById(Integer.valueOf(studentId));
    }

    @PostMapping("/newUser")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }




}
