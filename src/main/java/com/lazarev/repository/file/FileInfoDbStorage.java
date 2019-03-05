package com.lazarev.repository.file;

import com.lazarev.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileInfoDbStorage extends JpaRepository<File,Long> {

    @Query(value = "select * from file where file.storage_type='MEMORY'",nativeQuery = true)
    List<File> findAllInMemory();

    @Query(value = "select * from file where file.storage_type='LOGO'",nativeQuery = true)
    File getRegistrationLogo();

    @Query(value = "select * from file where file.storage_type='ErrorLogo'",nativeQuery = true)
    File getErrorLogo();
}
