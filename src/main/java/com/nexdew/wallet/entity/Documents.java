package com.nexdew.wallet.entity;

import com.nexdew.wallet.common.enums.DocumentType;
import com.nexdew.wallet.configuration.anotationValidate.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data // Create getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Documents {
        @Id
        @GeneratedValue
        private int id;


        @ManyToOne
        private User user;

        @Enumerated(EnumType.STRING)
        private DocumentType documentType;

        private String path;

        private LocalDate CreatedDate;
        private LocalDate ModifiedDate;



}



