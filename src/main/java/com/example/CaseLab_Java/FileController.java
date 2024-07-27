package com.example.CaseLab_Java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileService fileService;

    /**
     * POST-запрос на создание файла
     */
    @PostMapping
    public ResponseEntity create(@RequestBody File file){
        try{
            return ResponseEntity.ok(fileService.create(file));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id){
        try{
            return ResponseEntity.ok(fileService.get(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @ResponseBody
    @GetMapping
    public List<File> getAll(){
        return fileService.getAll();
    }
}
