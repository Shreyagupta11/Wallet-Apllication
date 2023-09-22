package com.nexdew.wallet.helper;

import com.nexdew.wallet.common.enums.AccType;
import com.nexdew.wallet.common.enums.Gender;
import com.nexdew.wallet.common.enums.UserRole;
import com.nexdew.wallet.dto.request.UserRequest;
import com.nexdew.wallet.entity.Account;
import com.nexdew.wallet.entity.User;
import com.nexdew.wallet.validate.ValidationsExcelCustomer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

//    public static boolean checkExcelFormat(MultipartFile file){
//        String contentType = file.getContentType();
//        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
//           return true;}
//        else {
//            return false;}
//    }

    public static void saveDataFromExcel(List<User> users,List<Account> accounts,List<UserRequest> requests) throws IOException {

        for(UserRequest request:requests){
            Account account = Account.builder().accType(AccType.valueOf(request.getAccType())).description(request.getDescription()).openingBalance(request.getOpeningBalance()).build();
            accounts.add(account);
            User user = User.builder().username(request.getUsername()).email(request.getEmail()).gender(Gender.valueOf(request.getGender())).appUserRoles(request.getAppUserRoles()).contact(request.getContact()).password(request.getPassword()).build();
            users.add(user);
        }
    }


}

