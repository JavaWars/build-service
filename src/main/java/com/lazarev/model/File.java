package com.lazarev.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class File {

    private Long id;
    private String filePath;
    private String fileName;
    private String storageType;

    public File() {
    }

    @Id @GeneratedValue
    @Column(name = "file_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "storage_type")
    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(id, file.id) &&
                Objects.equals(filePath, file.filePath) &&
                Objects.equals(fileName, file.fileName) &&
                Objects.equals(storageType, file.storageType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filePath, fileName, storageType);
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", storageType='" + storageType + '\'' +
                '}';
    }
}
