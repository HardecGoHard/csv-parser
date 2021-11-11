package com.laba18.csvparser.controller;

import com.laba18.csvparser.exception.DataParseCsvException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler
    public ModelAndView parseCsvException(DataParseCsvException ex) {
        ModelAndView modelAndView = new ModelAndView("some_error");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }
}
