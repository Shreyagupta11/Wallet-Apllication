package com.nexdew.wallet.dto.request;

import com.nexdew.wallet.common.enums.AccType;
import com.nexdew.wallet.common.enums.DocumentType;
import com.nexdew.wallet.configuration.anotationValidate.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocRequest {


   private byte[] image;
   @NotNull
   @NotBlank
   private  String username;
   @ValueOfEnum(enumClass = DocumentType.class)
   private String DocType;


}
