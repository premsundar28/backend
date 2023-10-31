package com.example.student3.controller;

import com.example.student3.model.Experience;
import com.example.student3.model.Faculty;
import com.example.student3.model.Student;
import com.example.student3.repository.ExperienceRepository;
import com.example.student3.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/Faculty")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @PostMapping("/addFaculty")
    @PreAuthorize("hasAuthority('admin')")
    public void addStudent(@RequestBody Faculty faculty) {
        facultyRepository.save(faculty);
    }

    @PostMapping("/addExperience")
    @PreAuthorize("hasAuthority('admin')")
    public void addStudent(@RequestBody Experience experience) {
        experienceRepository.save(experience);
    }

}
