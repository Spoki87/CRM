package com.crm.exception.domain;

import com.crm.exception.business.BusinessException;

public class IllegalTransitionException extends BusinessException {
    public IllegalTransitionException(String message) {
        super(message);
    }
}
