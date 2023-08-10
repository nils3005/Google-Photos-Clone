package de.nils.google.photos.clone.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("PHOTOS")
public class Photo {
    @Id
    Integer id;

    @NotEmpty
    String fileName;

    @NotEmpty
    String contentType;

    @NotEmpty
    String base64;
    @NotEmpty
    String lastmodified;

    public Photo(){
    }

    public String getFileName(){
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBase64() {
        return this.base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType){
        this.contentType = contentType;
    }

    public Integer id(){
        return this.id;
    }

    public String getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(String lastmodified) {
        this.lastmodified = lastmodified;
    }

    public Integer getId() {
        return this.id;
    }
}
