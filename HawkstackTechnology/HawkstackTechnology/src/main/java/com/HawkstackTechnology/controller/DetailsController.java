package com.HawkstackTechnology.controller;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.HawkstackTechnology.entity.Details;
import com.HawkstackTechnology.service.DetailsService;

@RestController
@RequestMapping("/api/details")
public class DetailsController {

    @Autowired
    private DetailsService service;

    @PostMapping
    public Details create(@RequestBody Details details) {
        return service.create(details);
    }

    @GetMapping
    public List<Details> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Details update(@PathVariable Long id, @RequestBody Details details) {
        return service.update(id, details);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }
    
    @PostMapping("/upload")
    public Details uploadDetails(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("pdf") MultipartFile pdf,
            @RequestParam("video") MultipartFile video
    ) throws IOException {

        String uploadDir = "uploads/";

        // Save PDF
        String pdfPath = uploadDir + pdf.getOriginalFilename();
        Files.copy(pdf.getInputStream(), Paths.get(pdfPath));

        // Save Video
        String videoPath = uploadDir + video.getOriginalFilename();
        Files.copy(video.getInputStream(), Paths.get(videoPath));

        // Save data in DB
        Details details = new Details();
        details.setName(name);
        details.setEmail(email);
        details.setPhone(phone);
        details.setAddress(address);
        details.setPdfPath(pdfPath);
        details.setVideoPath(videoPath);

        return service.create(details);
    }
    
}
