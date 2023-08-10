package de.nils.google.photos.clone.service;

import de.nils.google.photos.clone.model.Photo;
import de.nils.google.photos.clone.repository.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    public Photo save(Photo photo){
        photoRepository.save(photo);
        return photo;
    }

    public HashMap<String, ArrayList<Photo>> getPhotosOrderedByDate(){
        HashMap<String, ArrayList<Photo>> result = new HashMap<>();
        Iterable<Photo> iterable = photoRepository.findAll();
        iterable.forEach(photo -> {
            if(result.containsKey(photo.getLastmodified())){
                result.get(photo.getLastmodified()).add(photo);
            }else{
                ArrayList<Photo> list = new ArrayList<>();
                list.add(photo);
                result.put(photo.getLastmodified(), list);
            }
        });
        HashMap<String, ArrayList<Photo>> temp = result.entrySet()
                .stream()
                .sorted(
                        Map.Entry.<String, ArrayList<Photo>>comparingByKey().reversed()
                )
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)
                );
        return temp;
    }
}