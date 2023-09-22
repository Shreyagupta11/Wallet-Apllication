package com.nexdew.wallet.service.impl;

import com.nexdew.wallet.common.enums.AccType;
import com.nexdew.wallet.common.enums.Gender;
import com.nexdew.wallet.common.enums.UserRole;
import com.nexdew.wallet.dto.request.UserRequest;
import com.nexdew.wallet.entity.Account;
import com.nexdew.wallet.entity.User;
import com.nexdew.wallet.helper.ExcelHelper;
import com.nexdew.wallet.repository.AccountRepository;
import com.nexdew.wallet.repository.UserRepository;
import com.nexdew.wallet.service.IExcelService;
import com.nexdew.wallet.validate.ValidationsExcelCustomer;
import lombok.RequiredArgsConstructor;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService implements IExcelService {
            private final UserRepository userRepository;
            private final ModelMapper mapper;
            private final AccountRepository accountRepository;
        @Override
        public Workbook generateExcelFile() {
            Workbook workbook = new XSSFWorkbook(); // Create a new Excel workbook

            Sheet sheet = workbook.createSheet("Sheet1"); // Create a new sheet
            Row headerRow = sheet.createRow(0); // Create a header row

            // Create header cells
            Cell headerCell1 = headerRow.createCell(0);
            headerCell1.setCellValue("username");
            applyBoldFont(headerCell1,workbook);

            Cell headerCell2 = headerRow.createCell(1);
            headerCell2.setCellValue("email");
            applyBoldFont(headerCell2,workbook);

            Cell headerCell3 = headerRow.createCell(2);
            headerCell3.setCellValue("contact");
            applyBoldFont(headerCell3,workbook);

            Cell headerCell4 = headerRow.createCell(3);
            headerCell4.setCellValue("Gender");
            applyBoldFont(headerCell4,workbook);

            Cell headerCell5 = headerRow.createCell(4);
            headerCell5.setCellValue("Password");
            applyBoldFont(headerCell5,workbook);

            Cell headerCell6 = headerRow.createCell(5);
            headerCell6.setCellValue("UserRoles");
            applyBoldFont(headerCell6,workbook);

            Cell headerCell7 = headerRow.createCell(6);
            headerCell7.setCellValue("AccType");
            applyBoldFont(headerCell7,workbook);

            Cell headerCell8 = headerRow.createCell(7);
            headerCell8.setCellValue("AccDescription");
            applyBoldFont(headerCell8,workbook);

            Cell headerCell9 = headerRow.createCell(8);
            headerCell9.setCellValue("OpeningBalance");
            applyBoldFont(headerCell9,workbook);


            return workbook;
        }

        private void applyBoldFont(Cell cell, Workbook workbook) {
            Font boldFont = workbook.createFont();
            boldFont.setBold(true);

            CellStyle style = workbook.createCellStyle();
            style.setFont(boldFont);

            cell.setCellStyle(style);
        }




        @Override
        public List<User> getAllUsers(){
          return   this.userRepository.findAll();
        }

    @Override
    public List<UserRequest> convertExcelTOCustomerList(MultipartFile file) throws IOException {
        List<UserRequest> cs = new ArrayList<>();
        List<UserRequest> valid = new ArrayList<>();
        List<UserRequest> Invalid = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        int rowRum  =0;
        Iterator<Row> rowit = sheet.iterator();
        while(rowit.hasNext()){
            Row row = rowit.next();
            if(rowRum == 0)
            {rowRum++; continue;
            }
            Iterator<Cell> cells = row.iterator();
            UserRequest user = new UserRequest();
            while(cells.hasNext()) {
                Cell cell = cells.next();
                switch (cell.getColumnIndex()) {
                    case 0:
                        user.setUsername(cell.getStringCellValue());
                        break;
                    case 1:
                        user.setEmail(cell.getStringCellValue());
                        break;
                    case 2:
                        user.setContact(String.valueOf( cell.getNumericCellValue()));
                        break;
                    case 3:
                        user.setGender(cell.getStringCellValue());
                        break;

                    case 4:
                        user.setPassword(cell.getStringCellValue());
                        break;
                    case 5:
                        UserRole userRole = UserRole.valueOf(cell.getStringCellValue());
                        List<UserRole> cellValue = new ArrayList<>();
                        cellValue.add(userRole);
                        user.setAppUserRoles(cellValue);
                        break;
                    case 6:
                        user.setAccType(cell.getStringCellValue());
                        break;
                    case 7:
                        user.setDescription(cell.getStringCellValue());
                        break;
                    case 8:
                        user.setOpeningBalance(cell.getNumericCellValue());
                        break;
                    default:
                        break;
                }

            }
            cs.add(user);
        }
        ValidationsExcelCustomer validity = new ValidationsExcelCustomer(userRepository);
        validity.ValidCustomer(valid,Invalid,cs);
        List<User> users = new ArrayList<>();
        List<Account> accounts= new ArrayList<>();
        ExcelHelper.saveDataFromExcel(users,accounts,valid);

        List<Account> saved= accountRepository.saveAll(accounts);
        List<User> savedCustomers = userRepository.saveAll(users);
        return Invalid;
    }

    }



