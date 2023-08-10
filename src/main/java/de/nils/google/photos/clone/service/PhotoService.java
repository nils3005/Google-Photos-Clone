package de.nils.google.photos.clone.service;

import de.nils.google.photos.clone.model.Photo;
import de.nils.google.photos.clone.repository.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Iterable<Photo> getPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhotoByID(Integer id) {
        return photoRepository.findById(id).orElse(null);
    }

    public void removeByID(Integer id) {
        photoRepository.deleteById(id);
    }

    public Photo save(MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setFileName(file.getOriginalFilename());
        photo.setContentType(file.getContentType());
        photo.setBase64(Base64.getEncoder().encodeToString(file.getBytes()));
        photoRepository.save(photo);
        return photo;
    }

    public Photo save(Photo photo){
        photoRepository.save(photo);
        return photo;
    }
}