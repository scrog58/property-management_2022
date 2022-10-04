package com.mycompany.propertymanagement.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

//telling spring to use this as the central exception handling class. Look at this class for a paticular handler.
@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv) {
        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = null;
        List<FieldError> fieldErrorList = manv.getBindingResult().getFieldErrors();

        for(FieldError fe: fieldErrorList) {
            logger.info("BusinessException is thrown - level-info: {} - {}", fe.getField(), fe.getDefaultMessage());
            logger.debug("BusinessException is thrown - level - debug: {} - {}", fe.getField(), fe.getDefaultMessage());

            errorModel = new ErrorModel();
            errorModel.setCode(fe.getField());
            errorModel.setMessage(fe.getDefaultMessage());
            errorModelList.add(errorModel);
        }

        return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST);
    }

    //when ever a business exception in the BusinessException class is thrown come into this method
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex) {
        //System.out.println("BusinessException is thrown");
        for(ErrorModel em : bex.getErrors()) {
            logger.info("BusinessException is thrown - level-info: {} - {}", em.getCode(), em.getMessage());
            logger.debug("BusinessException is thrown - level - debug: {} - {}", em.getCode(), em.getMessage());
            logger.warn("BusinessException is thrown - level-warn: {} - {}", em.getCode(), em.getMessage());
            logger.error("BusinessException is thrown - level-error: {} - {}", em.getCode(), em.getMessage());
        }
        return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }

}
