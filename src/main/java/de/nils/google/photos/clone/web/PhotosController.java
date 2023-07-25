package de.nils.google.photos.clone.web;

import de.nils.google.photos.clone.model.Photo;
import de.nils.google.photos.clone.service.PhotoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PhotosController {
    private final PhotoService photoService;

    public PhotosController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/photos") //"/"
    public Iterable<Photo> getPhotos(){
        return photoService.getPhotos();
    }

    @PostMapping("/photos") //"/"
    public Photo postPhoto(@RequestPart("data") MultipartFile file) throws IOException {
        return photoService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }
}
