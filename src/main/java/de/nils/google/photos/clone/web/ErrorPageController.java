package de.nils.google.photos.clone.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {
    @GetMapping("/errorPage")
    public String error(){
        return "errorPage";
    }
}
