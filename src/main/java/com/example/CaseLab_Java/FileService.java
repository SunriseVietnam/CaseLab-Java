package com.example.CaseLab_Java;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Сервис файла
 */
@Service
public class FileService {
    @Autowired
    private FileRepo fileRepo;

    public Long create(File file){
        LocalDateTime now = LocalDateTime.now();
        var savedFile = fileRepo.save(new FileEntity(file.getFile() ,file.getTitle(), LocalDateTime.now(), file.getDescription()));
        return savedFile.getId();
    }
    public File get(Long id){
        FileEntity file = fileRepo.findById(id).get();
        return new File(file.getFile(), file.getTitle(), file.getCreationDate(), file.getDescription());
    }
    public List<File> getAll(){
        ArrayList<File> filesDTO = new ArrayList<File>();
        List<FileEntity> files = (List<FileEntity>) fileRepo.findAll();
        ModelMapper modelMapper = new ModelMapper();
        for (FileEntity file : files) {
            filesDTO.add(modelMapper.map(file, File.class));
        }
        filesDTO.sort(Comparator.comparing(File::getCreationDate));
        return filesDTO;
    }
}
