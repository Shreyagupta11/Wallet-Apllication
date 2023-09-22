package com.nexdew.wallet.dto.request;

import com.nexdew.wallet.common.enums.Gender;
import com.nexdew.wallet.common.enums.UserRole;
import com.nexdew.wallet.configuration.anotationValidate.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {


    @NotNull(message = "First name can not be null")
    @NotBlank(message = "Firstname can not be blank")
    private String username;

    @NotNull(message = "Email can not be null")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message="Email address must be valid")
    @NotBlank(message = "Email can be blank")
    private String email;


    @NotNull(message = "Contact can not be null")
    @NotBlank(message = "Contact can not be null")
    private String contact;

    @ValueOfEnum(enumClass = Gender.class)
    @NotNull
    @NotBlank
    private String gender;


    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,20}$",message = "Password have min 6 and max 20 character along with 1 capital ,1 small 1 numeric and 1 special character")
    @NotBlank(message = "Password can not be blank ")
    private String password;


    private List<UserRole> appUserRoles;

    @NotNull
    @NotBlank
    private  String description;
    @NotNull
    private double openingBalance;

    @NotNull
    @NotBlank
    private String accType;

    private String validateMessage;

}
