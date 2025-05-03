package com.crm.exception.domain;

import com.crm.exception.business.BusinessException;

public class InvalidDataException extends BusinessException {
    public InvalidDataException(String message) {
        super(message);
    }
}
