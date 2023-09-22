package com.nexdew.wallet.validate;

import com.nexdew.wallet.dto.request.UserRequest;
import com.nexdew.wallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ValidationsExcelCustomer
{
    private final UserRepository userRepository;

    public void  ValidCustomer(List<UserRequest> Valid , List<UserRequest> Invalid , List<UserRequest> cs)
    {
        for(UserRequest c :cs)
        {
            boolean isValid = true;
            StringBuilder errorMessage = new StringBuilder();

            if (c.getUsername() == null) {
                errorMessage.append("username is null.");
                isValid = false;
            }

            if (c.getEmail() == null) {
                errorMessage.append("Email Id is null. ");
                isValid = false;
            }
            if (c.getContact() == null) {
                errorMessage.append("Contact No is null. ");
                isValid = false;
            }
            if (c.getGender() == null) {
                errorMessage.append("Gender is null. ");
                isValid = false;
            }
            if (c.getPassword() == null) {
                errorMessage.append("Password is null. ");
                isValid = false;
            }
            if (c.getAccType() == null) {
                errorMessage.append("Account type is null ");
                isValid = false;
            }
            if (c.getAppUserRoles() == null) {
                errorMessage.append("Role is null ");
                isValid = false;
            }
            if (c.getDescription() == null) {
                errorMessage.append("Description is null. ");
                isValid = false;
            }
            if (c.getOpeningBalance()==0) {
                errorMessage.append("State is null. ");
                isValid = false;
            }


            if (isValid) {
                // Check for duplicate email before adding to valid list
                if (userRepository.findByEmail(c.getEmail())!=null) {
                    isValid = false;
                    System.out.println(".................................................................."+ userRepository.findByEmail(c.getEmail()));
                    errorMessage.append("User Already Exists");
                } else {
                    System.out.println("add valid customer");
                    Valid.add(c);
                }
            }

            if (!isValid) {
                c.setValidateMessage(errorMessage.toString());
                Invalid.add(c);
            }
        }

    }

}
