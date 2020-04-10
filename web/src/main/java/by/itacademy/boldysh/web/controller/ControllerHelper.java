package by.itacademy.boldysh.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerHelper {

    @ExceptionHandler(Exception.class)
    public String error(Model model, Exception ex) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }
}
