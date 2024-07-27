package com.example.CaseLab_Java;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "file", nullable = false)
    private String file;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;
    @Column(name = "description", nullable = false)
    private String description;

    public FileEntity(String file, String title, LocalDateTime creationDate, String description) {
        this.file = file;
        this.title = title;
        this.creationDate = creationDate;
        this.description = description;
    }
}
