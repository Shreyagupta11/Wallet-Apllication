package com.nexdew.wallet.entity;

import com.nexdew.wallet.common.enums.Gender;
import com.nexdew.wallet.common.enums.UserRole;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity<Long> implements Serializable {

  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
  @Column(unique = true, nullable = false,name = "username")
  private String username;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(nullable = false)
  private String contact;

  @Column(nullable = false)
  @Size(min = 6, max = 200, message = "Minimum password length: 8 characters")
  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  private List<UserRole> appUserRoles;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Account> accounts;

  @Column
  private LocalDate expireDate;

  public User(String username){
    this.username=username;
  }

  @Override
  public String toString() {
    return "User{" +
            "username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", gender=" + gender +
            ", contact='" + contact + '\'' +
            ", password='" + password + '\'' +
            ", appUserRoles=" + appUserRoles +
            ", expireDate=" + expireDate +
            '}';
  }
}
