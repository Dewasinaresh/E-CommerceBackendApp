package com.nd.electronic.web.MTechDistributions.exceptionUtils;

import com.nd.electronic.web.MTechDistributions.DTOS.ApiResponseMassage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<ApiResponseMassage> ResourceNotFoundException(Exception e){
        final ApiResponseMassage responseToApiCall = ApiResponseMassage.builder().massage(e.getMessage()).success(true).HttpStatus(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(responseToApiCall,HttpStatus.OK) ;

    }
    //For MethodArgumentNotFoundException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> MethodArgumentNotFoundHandler( MethodArgumentNotValidException ex){
        final List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        final Map<String,Object> errorListMap=new HashMap<>();
        allErrors.stream().forEach( ObjectError->{
            final String defaultMessage = ObjectError.getDefaultMessage();
            final String field = ((FieldError) ObjectError).getField();
            errorListMap.put(field,defaultMessage);

        }

        );
        return new  ResponseEntity<>(errorListMap,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiResponseMassage> BadApiReq(BadApiRequest e){
        final ApiResponseMassage responseToApiCall = ApiResponseMassage.builder().massage(e.getMessage()).success(false).HttpStatus(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(responseToApiCall,HttpStatus.BAD_REQUEST) ;

    }

}
