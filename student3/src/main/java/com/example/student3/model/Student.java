package com.example.student3.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stu")
public class Student {
    @Id
    int studentId;

    String studentName;

    long studentMobileNumber;

}
/*
{
  "telugu": 90,
  "hindi": 85,
  "english": 95,
  "maths": 92,
  "science": 88,
  "social": 93,
  "studentId": {
    "studentId": 1
  }
}

 */

