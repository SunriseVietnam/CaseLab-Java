package com.example.CaseLab_Java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * Модель файла для отправки JSON файлом
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    private Long id;
    private String file;
    private String title;
    private LocalDateTime creationDate;
    private String description;

    public File(String file, String title, LocalDateTime creationDate, String description) {
        this.file = file;
        this.title = title;
        this.creationDate = creationDate;
        this.description = description;
    }

    public File(String file, String title, String description) {
        this.file = file;
        this.title = title;
        this.description = description;
    }
}
