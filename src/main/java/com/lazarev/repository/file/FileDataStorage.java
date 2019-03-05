package com.lazarev.repository.file;

import com.lazarev.model.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileDataStorage {

    //return path on disk or null (in memory)
    String insert(MultipartFile multipartFile, File fileInfo,String additionalDirPath);
    String insert(MultipartFile multipartFile, File fileInfo);
    //return bytes of file
    byte[] get(Long id,String path);
    //remove only fileData, not info about file in db
    void delete(Long FileId,String path);

}
