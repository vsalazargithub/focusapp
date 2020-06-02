package com.focus.studio.app.config.exception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BadRequestAlertException extends RuntimeException {

    private final String entityName;

    private final String errorKey;
    
    private final String defaultMessage; 


	public BadRequestAlertException(String defaultMessage,String entityName, String errorKey) {
		super(String.format(new StringBuilder().append("entityName:%s errorKey:%s defaultMessage:%s").toString(),entityName,errorKey,defaultMessage));
		this.entityName = entityName;
		this.errorKey = errorKey;
		this.defaultMessage = defaultMessage;
	}

	public String getEntityName() {
		return entityName;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}
	

}
