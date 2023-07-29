package de.nils.google.photos.clone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Objects;

@Table("PHOTOS")
public class Photo {
    @Id
    Integer id;

    @NotEmpty
    String fileName;

    @NotEmpty
    String contentType;

    @NotEmpty
    String data;


    public Photo(){
    }

    public String getFileName(){
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType){
        this.contentType = contentType;
    }

    public String getBase64(){
        return data;
    }

    public Integer getId(){
        return this.id;
    }
}
