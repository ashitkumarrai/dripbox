package com.example.dripbox.controller;

import com.example.dripbox.entity.File;
import com.example.dripbox.repository.FileRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
public class FileController {

    @Autowired
    FileRepository fr;

    @PostMapping(value = "/media/upload",consumes=  "multipart/form-data")
    public @ResponseBody ResponseEntity<Object> uploadmedia(HttpServletRequest request,
                                                            @Parameter(description = "File to upload") @RequestParam(value = "file")
                                                            @Schema(type = "string", format = "binary") MultipartFile file) {
        try {

            String name = file.getOriginalFilename();
            log.info(name);

            if (name == null || name.contains("..") || name.contains(" ")) {
                Map<String, String> hasMap = new HashMap<>();

                hasMap.put("Error",
                        "Sorry! Filename contains invalid path sequence, correct name format before upload");

                return new ResponseEntity<>(hasMap, HttpStatus.BAD_REQUEST);

            }

            byte[] mediaData = file.getBytes();

            File f = File.builder().media(mediaData)

                    .mediaContentType(file.getContentType())

                    .id(UUID.randomUUID().toString() + file.getOriginalFilename()).build();
            f.setMediaUrl(new URI("/media/show/" + f.getId()).toString());
            f.setFileOriginalName(file.getOriginalFilename());

            fr.save(f);



            return new ResponseEntity<>(f, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Exception: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/media/show/{id}")
    @ResponseBody

    void showMedia(@PathVariable("id") String id, HttpServletResponse response)
            throws IOException, Exception {
        File dbSavedFile = fr.findById(id)
                .orElseThrow(() -> new Exception("media not found with id: " + id));

        response.setContentType(dbSavedFile.getMediaContentType());
        response.getOutputStream().write(dbSavedFile.getMedia());
        response.getOutputStream().close();

    }

    @GetMapping("/media/show/all")
    List<File> showAllMedia() {

        return fr.findAll();
    }

}
