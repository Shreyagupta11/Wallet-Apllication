package com.nexdew.wallet.controller;

import com.nexdew.wallet.common.enums.DocumentType;
import com.nexdew.wallet.constants.ApiConstant;

import com.nexdew.wallet.dto.request.DocRequest;
import com.nexdew.wallet.dto.response.ApiResponse;
import com.nexdew.wallet.repository.DocRepository;
import com.nexdew.wallet.repository.UserRepository;
import com.nexdew.wallet.service.impl.DocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@RestController
@Slf4j
@RequestMapping("/images")
public class DocController {
        @Autowired
        private DocRepository docRepository;

        @Autowired
        private DocService docService;

        @Autowired
        private UserRepository userRepository;

    @PostMapping(value = "/uploadDocuments/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') ")
    public String uploadFileSystem(@RequestParam("file") MultipartFile file, @PathVariable long userId,@RequestParam("type") DocumentType type) throws IOException
    {
        log.info("hi");
        String uploadImage = docService.uploadFileSystem(file,userId,type);
        return "Image Uploaded Successfully";
    }

    @GetMapping(value = "/Documents/{userId}/{Doc}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') ")
    public ResponseEntity<byte[]>downloadImage(@PathVariable  long userId ,@PathVariable DocumentType Doc) throws IOException {
        byte[] imageData = docService.downloadImage(userId,Doc);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
    }


}