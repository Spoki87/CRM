package com.crm.exception.domain;

import com.crm.exception.business.BusinessException;

public class EmailAlreadyTakenException extends BusinessException {
    public EmailAlreadyTakenException() {
        super("Email already taken");
    }
}
