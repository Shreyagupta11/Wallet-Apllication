package com.nexdew.wallet.dto;

import com.nexdew.wallet.common.enums.DocumentType;
import com.nexdew.wallet.configuration.anotationValidate.ValueOfEnum;

import java.time.LocalDate;

public class DocDto {
    @ValueOfEnum(enumClass = DocumentType.class)
    private DocumentType documentType;

    private String path;

    private LocalDate CreatedDate;
    private LocalDate ModifiedDate;




}
