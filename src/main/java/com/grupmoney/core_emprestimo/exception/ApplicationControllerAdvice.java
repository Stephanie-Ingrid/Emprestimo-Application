package com.grupmoney.core_emprestimo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ApplicationControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ApiErros handleBadRequestException(MethodArgumentNotValidException e) {

        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        List<String> errorsList = allErrors.stream().filter(erro -> Objects.nonNull(erro.getDefaultMessage()))
                .map(erro -> erro.getDefaultMessage()).collect(Collectors.toList());

        return ApiErros.builder()
                .erros(errorsList)
                .build();
    }

    @ExceptionHandler(DadosDuplicadosException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ApiErros handleBadRequestDuplicateException(DadosDuplicadosException e) {
        return new ApiErros(e.getMessage());
    }

    @ExceptionHandler(DataBaseException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ApiErros handleDataBaseException(DataBaseException e) {
        return new ApiErros(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ApiErros handleNotFoundException(NotFoundException e) {
        return new ApiErros(e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ApiErros handleBadRequestException(BadRequestException e) {
        return new ApiErros(e.getMessage());
    }

    @ExceptionHandler(IntegrationErrorException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiErros handleIntegrationException(IntegrationErrorException e) {
        return new ApiErros("Sistema indisponivel, por favor tente mais tarde");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiErros handleGeneralException(Exception e) {
        log.error(e.getMessage(), e);

        return new ApiErros("Sistema Indisponivel, por favor tente mais tarde ou contate um administrador!");
    }


}
