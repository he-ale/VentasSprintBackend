package com.mito.facturacionAppbackend.exception;

//esta redundando con ResponseExceptionHandler por eso se comenta la linea de abajo
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModelNotFountException extends RuntimeException{

    public ModelNotFountException(String msg){
        super(msg);
    }
}
