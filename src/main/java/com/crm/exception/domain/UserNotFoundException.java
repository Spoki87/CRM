package com.crm.exception.domain;

import com.crm.exception.business.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super("User not found");
    }
}
