package com.crm.exception.domain;

import com.crm.exception.business.BusinessException;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(){
        super("Resource not found");
    }
}
