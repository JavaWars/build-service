package com.lazarev.repository.file;

import com.lazarev.exception.BuildServiceApplicationException;
import com.lazarev.model.File;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FileMemoryStorage implements FileDataStorage {

    private Map<Long,byte[]> data=new HashMap<>();

    @Autowired
    @Qualifier("fileDiskStorage")
    private FileDiskStorage fileDiskStorage=new FileDiskStorage();

    @Override
    public String insert(MultipartFile multipartFile, File fileInfo, String additionalDirPath) {
        if (data.size()>20) throw new BuildServiceApplicationException("File memory storage limit");

        String savePathLocation=fileDiskStorage.insert(multipartFile,fileInfo,additionalDirPath);

        if (fileInfo.getStorageType()==null) {//is already defined like ERR, LOGO, ERR_ACCESS
            fileInfo.setStorageType("MEMORY");
        }

        try {
            data.put(fileInfo.getId(), multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return savePathLocation;
    }

    @Override
    public String insert(MultipartFile multipartFile, File fileInfo) {
        return insert(multipartFile,fileInfo,"memory/");
    }

    @Override
    public byte[] get(Long id,String path) {
        byte [] result=null;
        result=data.get(id);
        if (result==null){
            result=fileDiskStorage.get(id,path);
        }
        return result;
    }

    @Override
    public void delete(Long id,String path) {
        fileDiskStorage.delete(id,path);
        data.remove(id);
    }

    public void startupLoading(List<Pair<Long,byte[]> > startupData) {
        for (Pair<Long,byte[]> el:startupData){
            data.put(el.getKey(),el.getValue());
        }

    }
}
