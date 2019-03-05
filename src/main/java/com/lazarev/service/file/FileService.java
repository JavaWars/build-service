package com.lazarev.service.file;

import com.lazarev.model.File;
import javafx.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface FileService {
    Pair<File,byte[]> getById(Long fileId);
    boolean exist(Long fileId);
    void save(MultipartFile multipartFile);
    void delete(Long fileId);

}
