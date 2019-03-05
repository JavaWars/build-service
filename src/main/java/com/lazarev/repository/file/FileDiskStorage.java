package com.lazarev.repository.file;

import javafx.util.Pair;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

@Repository
public class FileDiskStorage implements FileDataStorage {

    private static final String PATH="C:/data/";

    @Override
    public String insert(MultipartFile multipartFile, com.lazarev.model.File fileInfo,String additionalPath) {
        fileInfo.setStorageType("DISK");

        String filePathOnDisk=null;

        try {
            String fileName = fileInfo.getId().toString();
            InputStream fileContent = multipartFile.getInputStream();

            byte[] buffer = new byte[fileContent.available()];
            fileContent.read(buffer);

            filePathOnDisk=PATH +additionalPath+ String.valueOf(fileInfo.getId());
            File targetFile = new File(filePathOnDisk);
            targetFile.getParentFile().mkdirs();
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
            outStream.flush();
            outStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return filePathOnDisk;
    }

    @Override
    public String insert(MultipartFile multipartFile, com.lazarev.model.File fileInfo) {
        return insert(multipartFile,fileInfo,"");
    }

    @Override
    public byte[] get(Long id,String path) {
        //return file byte[] from disk
        File file=new File(path);
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Long id,String path) {
        //delete file from disk
        //absolute file name with path
        File file = new File(path);
        if(file.delete()){
            System.out.println("file"+path+" deleted");
        }
    }

    public List<Pair<Long,byte[]>> getMemoryStoredDataFromDisk(List<com.lazarev.model.File> memoryStoredFile) {
        List<Pair<Long,byte[]> > result=new LinkedList<>();

        for(com.lazarev.model.File diskFile:memoryStoredFile){
            result.add(new Pair<>(diskFile.getId(),get(diskFile.getId(),diskFile.getFilePath())));
        }

        return result;
    }
}
