package com.lazarev.configuration;

import com.lazarev.repository.file.FileDataStorage;
import com.lazarev.repository.file.FileDiskStorage;
import com.lazarev.repository.file.FileMemoryStorage;
import com.lazarev.service.file.FileService;
import com.lazarev.service.file.FileServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileServiceConfig {

    @Bean
    FileService diskFileService(){
        FileServiceImpl fs=new FileServiceImpl();
        fs.setFileDataStorage(new FileDiskStorage());
        return fs;
    }

    @Bean()
    FileService memoryFileService(){
        FileServiceImpl fs=new FileServiceImpl();
        fs.setFileDataStorage(new FileMemoryStorage());
        return fs;
    }

}
