package com.lazarev.service.file;

import com.lazarev.exception.BuildServiceApplicationException;
import com.lazarev.model.File;
import com.lazarev.repository.file.FileDataStorage;
import com.lazarev.repository.file.FileInfoDbStorage;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class FileServiceImpl implements FileService{

    //total info about file(id,name...) in db without file bytes
    @Autowired
    private FileInfoDbStorage fileInfoDbStorage;

    private FileDataStorage fileDataStorage;

    public void setFileDataStorage(FileDataStorage fileDataStorage) {
        this.fileDataStorage = fileDataStorage;
    }

    public Pair<com.lazarev.model.File,byte[]> getById(Long fileId){

        if (!fileInfoDbStorage.existsById(fileId)) throw new BuildServiceApplicationException("file with id does not exist");
        com.lazarev.model.File myFileInfo=fileInfoDbStorage.getOne(fileId);
        return new Pair<>(myFileInfo,fileDataStorage.get(fileId,myFileInfo.getFilePath()));

    }

    public boolean exist(Long fileId){
        return fileInfoDbStorage.existsById(fileId);
    }

//    @Transactional
    public void save(MultipartFile multipartFile){
        System.out.println("saving file");
        //creating info object (will we saved in db)
        com.lazarev.model.File fileInfo=new File();
        //save original filename
        fileInfo.setFileName(multipartFile.getOriginalFilename());
        //save addition fileinfo
        fileInfoDbStorage.save(fileInfo);
        System.out.println("file info inserted"+fileInfo.getId());
        //save file on DISK OR in MEMORY
        fileInfo.setFilePath(fileDataStorage.insert(multipartFile,fileInfo));
        //update info setting file path on disk
        fileInfoDbStorage.save(fileInfo);
    }

//    @Transactional
    public void delete(Long fileId){
        if (exist(fileId))
        {
            fileDataStorage.delete(fileId,fileInfoDbStorage.getOne(fileId).getFilePath());
            fileInfoDbStorage.deleteById(fileId);
        }
    }

}
