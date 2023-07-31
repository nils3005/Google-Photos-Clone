package de.nils.google.photos.clone.web;

import de.nils.google.photos.clone.model.Photo;
import de.nils.google.photos.clone.service.PhotoService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Controller
public class PhotosController {
    @Autowired
    private final PhotoService photoService;

    public PhotosController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/")
    public String getPhotos(Model model) {
        Iterable<Photo> iterable = photoService.getPhotos();
        ArrayList<Photo> photos = new ArrayList<>();
        iterable.forEach(photos::add);
        model.addAttribute("photos", photos);
        return "index";
    }

    @PostMapping("/")
    public String upload(@RequestPart ("data") MultipartFile file) throws IOException {
        photoService.save(file);
        String url = "redirect:/index";
        return url;
    }

    @DeleteMapping("/")
    public String delete(@RequestPart ("data") Photo photo){
        photoService.removeByID(photo.getId());
        return "index";
    }
}
