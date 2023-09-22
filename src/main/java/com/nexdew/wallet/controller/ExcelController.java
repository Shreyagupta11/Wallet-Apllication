package com.nexdew.wallet.controller;
import com.nexdew.wallet.constants.ApiConstant;
import com.nexdew.wallet.dto.UserDto;
import com.nexdew.wallet.dto.request.UserRequest;
import com.nexdew.wallet.dto.response.ApiResponse;
import com.nexdew.wallet.service.impl.ExcelService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;
    private  final ModelMapper mapper;


    @PostMapping("/upload")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') ")
    public ResponseEntity<ApiResponse> uploadExcelData(@Valid @RequestParam("file") MultipartFile file) throws IOException {

        List<UserRequest> userRequests = excelService.convertExcelTOCustomerList(file);
        UserDto userDto = this.mapper.map(userRequests, UserDto.class);
        return  ResponseEntity.ok(new ApiResponse("Data uploaded successfully", userDto, HttpStatus.CREATED));

    }

    @GetMapping("/download")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') ")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        Workbook workbook = excelService.generateExcelFile();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDisposition(ContentDisposition.attachment().filename("SampleExcelData.xlsx").build());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
