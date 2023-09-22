package com.nexdew.wallet.service;

import com.nexdew.wallet.dto.request.UserRequest;
import com.nexdew.wallet.entity.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IExcelService {
    public Workbook generateExcelFile();
    public List<User> getAllUsers();
//    public void saveDataFromExcel(MultipartFile file) throws IOException;
    public List<UserRequest> convertExcelTOCustomerList(MultipartFile file) throws IOException;
}

