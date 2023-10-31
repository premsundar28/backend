package com.example.student3.repository;

import com.example.student3.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certification,Long> {
    List<Certification> findByName(String name);
}
