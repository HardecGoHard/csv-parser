package com.laba18.csvparser.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ServerErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                modelAndView.setStatus(HttpStatus.NOT_FOUND);
                modelAndView.addObject("message", "Oh.... Page not found");
                return modelAndView;
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                modelAndView.addObject("message", "Oh.... Server error, try again later");
                return modelAndView;
            }
        }
        modelAndView.addObject("message", "Something went wrong....");
        return modelAndView;
    }

}
