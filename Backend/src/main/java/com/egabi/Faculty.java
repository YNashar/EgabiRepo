package com.egabi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "faculties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer facultyId;
  private String facultyName;
}
