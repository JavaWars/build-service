package com.lazarev.service.file;

import com.lazarev.exception.BuildServiceApplicationException;
import com.lazarev.exception.NoSuchLogoType;
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
    public void save(MultipartFile multipartFile,com.lazarev.model.File baseData){
        System.out.println("saving file");
        //save original filename
        baseData.setFileName(multipartFile.getOriginalFilename());
        //save addition fileinfo
        fileInfoDbStorage.save(baseData);
        System.out.println("file info inserted"+baseData.getId());
        //save file on DISK OR in MEMORY
        baseData.setFilePath(fileDataStorage.insert(multipartFile,baseData));
        //update info setting file path on disk
        fileInfoDbStorage.save(baseData);
    }

    public void save(MultipartFile multipartFile,String logoType){
        System.out.println("logoType"+logoType);

        if ((logoType==null) || (logoType.equalsIgnoreCase("MEMORY"))){File f=new File();f.setStorageType("MEMORY");save(multipartFile,f);}
        else {

            if ("LOGO".equalsIgnoreCase(logoType)
                    ||"ERR".equalsIgnoreCase(logoType)
                    ||"ERR_ACCESS".equalsIgnoreCase(logoType)){
                File oldFile=fileInfoDbStorage.findByStorageType(logoType);
                if (oldFile!=null) {
                    System.out.println("ald file storage type");
                    fileInfoDbStorage.deleteById(oldFile.getId());
                    fileDataStorage.delete(oldFile.getId(), oldFile.getFilePath());
                }
                else{
                    oldFile=new File();
                    oldFile.setStorageType(logoType);
                }
                File newFile=new File();
                newFile.setStorageType(oldFile.getStorageType());
                save(multipartFile,newFile);
            }
            else{
                System.out.println("No such logo type");
                throw new NoSuchLogoType("No such logo type");
            }
        }
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
