package com.nexdew.wallet.service;

import com.nexdew.wallet.common.enums.DocumentType;
import com.nexdew.wallet.dto.DocDto;
import com.nexdew.wallet.dto.request.DocRequest;
import com.nexdew.wallet.entity.Documents;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface IDocService {
    public DocDto upload(DocRequest request,String username);
    public String uploadFileSystem(MultipartFile file, long userId, DocumentType type) throws IOException;
    public byte[] downloadImage(long userId, DocumentType Doc) throws IOException;
//    public Optional<DocDto> getImageById(Long id);
}


