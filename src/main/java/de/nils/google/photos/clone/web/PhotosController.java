package de.nils.google.photos.clone.web;

import de.nils.google.photos.clone.model.Photo;
import de.nils.google.photos.clone.service.PhotoService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Controller
public class PhotosController {
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

    @PostMapping(path = "/post", consumes = "application/json", produces = "application/json")
    //@ResponseBody
    public String post (@RequestBody Photo photo){
        photoService.save(photo);
        return "redirect:/";
    }

    @RequestMapping(path = "/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@PathVariable("id") Integer id) {
        Photo photo = photoService.getPhotoByID(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        byte[] data = Base64.decodeBase64(photo.getBase64());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition build = ContentDisposition.builder("attachement")
                .filename(photo.getFileName())
                .build();
        headers.setContentDisposition(build);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        photoService.removeByID(id);
        return "index";
    }

    @RequestMapping("/info")
    public String infoPage() {
        return "info";
    }

    @GetMapping("/errorPage")
    public String error() {
        return "errorPage";
    }
}