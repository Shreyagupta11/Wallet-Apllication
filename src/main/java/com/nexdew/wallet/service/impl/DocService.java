package com.nexdew.wallet.service.impl;

import com.nexdew.wallet.common.enums.DocumentType;

import com.nexdew.wallet.configuration.exceptionconfig.CustomException;
import com.nexdew.wallet.dto.DocDto;
import com.nexdew.wallet.dto.request.DocRequest;
import com.nexdew.wallet.entity.Documents;
import com.nexdew.wallet.entity.User;
import com.nexdew.wallet.repository.DocRepository;
import com.nexdew.wallet.repository.UserRepository;
import com.nexdew.wallet.service.IDocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class DocService implements IDocService {

    @Autowired
    private DocRepository docRepository;
    @Autowired
    private UserRepository userRepository;
@Autowired
    private final ModelMapper modelMapper;
    @Override
    public DocDto upload(DocRequest request,String username) {

        if(userRepository.existsByUsername(username)){
            String username1 = request.getUsername();
            Documents documents = Documents.builder().user(userRepository.findByUsername(username1))
                    .documentType(DocumentType.valueOf(request.getDocType()))
                  .build();
            Documents documents1 = this.docRepository.save(documents);
            DocDto docDto = this.modelMapper.map(documents1, DocDto.class);
            return docDto;
        }
        return null;
    }

        private final String Folder_path = "H:/images/";
        @Override
        public String uploadFileSystem(MultipartFile file, long customerId, DocumentType type) throws IOException {

            String filepath = Folder_path + file.getOriginalFilename();
            String format = file.getOriginalFilename();
            String like = format.substring(format.lastIndexOf(".")+1);
            log.info(like);
            System.out.println(like+",,,,,,.....................");

            System.out.println(type);

            System.out.println(customerId+"................");


            if (!like.equals("png") && !like.equals("jpeg") && !like.equals("jpg")) {
                throw new CustomException("Format Is Unsupported");
            }

            Optional<User> cus = userRepository.findById(customerId);
            if(cus.isEmpty())
            {
                throw new CustomException("User DoesNot Exist");
            }
            Optional<Documents> op = docRepository.findByUserIdAndDocumentType(customerId, type);
            System.out.println(op+"...................");
            if(op.isPresent())
            {
                throw new CustomException("User has already this document ");
            }
            Documents cr = docRepository.save(Documents.builder()
                    .documentType(type)
                    .user(cus.get())
                    .path(filepath)
                    .CreatedDate(LocalDate.now()).ModifiedDate(LocalDate.now()).build());

            file.transferTo(new File(filepath));

            if(cr != null)
            {
                return "file Upload Successfully" + filepath;
            }

            return null;
        }

        @Override
        public byte[] downloadImage(long userId, DocumentType Doc) throws IOException {

            Documents userDoc = docRepository.findByUserIdAndDocumentType(userId, Doc)
                                .orElseThrow(() -> new CustomException("User DoesNot Exist"));
            String imagePath=userDoc.getPath();
            Path omg= Paths.get(imagePath);
            byte[] images = Files.readAllBytes(omg);
            return images;
        }
    }

