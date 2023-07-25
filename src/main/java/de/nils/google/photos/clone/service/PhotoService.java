package de.nils.google.photos.clone.service;

import de.nils.google.photos.clone.model.Photo;
import de.nils.google.photos.clone.repository.PhotoRepository;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Iterable<Photo> getPhotos(){
        return photoRepository.findAll();
    }

    public Photo getPhotoByID(Integer id){
        return photoRepository.findById(id).orElse(null);
    }

    public void remove(Integer id){
        photoRepository.deleteById(id);
    }

    public Photo save(String fileName, String contentType, byte[] data){
        Photo photo = new Photo();
        photo.setContentType(contentType);
        photo.setFileName(fileName);
        photo.setData(data);
        photoRepository.save(photo);
        return photo;
    }
}
