package com.nexdew.wallet.common.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
  ROLE_ADMIN, ROLE_MANAGER, ROLE_CUSTOMER;

  public String getAuthority() {
    return name();
  }

}
