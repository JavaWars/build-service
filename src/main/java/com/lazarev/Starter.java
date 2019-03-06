package com.lazarev;

import com.lazarev.repository.file.FileDiskStorage;
import com.lazarev.repository.file.FileInfoDbStorage;
import com.lazarev.repository.file.FileMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Pavel on 18.02.2019.
 */
@SpringBootApplication
public class Starter implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }

    @Autowired
    private FileMemoryStorage fileMemoryStorage;

    @Autowired
    private FileDiskStorage fileDiskStorage;

    @Autowired
    private FileInfoDbStorage fileInfoStorage;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        fileMemoryStorage.startupLoading(
                fileDiskStorage.getMemoryStoredDataFromDisk(
                        fileInfoStorage.findAllInMemory()));
    }
}
