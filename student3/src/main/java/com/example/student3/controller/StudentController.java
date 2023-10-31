package com.example.student3.controller;

import com.example.student3.model.*;
import com.example.student3.repository.*;
import com.example.student3.service.JwtService;
import com.example.student3.Entity.UserInfo;
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
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private StudentQuery service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    InternshipRepository internshipRepository;

    @Autowired
    CertificateRepository certificateRepository;


    @PostMapping("addStudent")
    @PreAuthorize("hasAuthority('admin')")
    public void addStudent(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @PostMapping("addSkill")
    @PreAuthorize("hasAuthority('admin')")
    public void addSkill(@RequestBody Skill skill){
        skillRepository.save(skill);
    }

    @PostMapping("addProject")
    @PreAuthorize("hasAuthority('admin')")
    public void addProject(@RequestBody Project project){
        projectRepository.save(project);
    }

    @PostMapping("addInternship")
    @PreAuthorize("hasAuthority('admin')")
    public void addInternship(@RequestBody Internship internship){
        internshipRepository.save(internship);
    }

    @PostMapping("addCertificate")
    @PreAuthorize("hasAuthority('admin')")
    public void addCertification(@RequestBody Certification certification){
        certificateRepository.save(certification);
    }

    @GetMapping("showAllStudents")
    @PreAuthorize("hasAuthority('admin')")
    public List<Student> showAllStudents() {
        return studentRepository.findAll();
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
