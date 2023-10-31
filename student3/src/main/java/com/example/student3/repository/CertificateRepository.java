package com.example.student3.repository;

import com.example.student3.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certification,Long> {
}
