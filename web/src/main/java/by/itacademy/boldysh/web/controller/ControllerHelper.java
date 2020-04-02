package by.itacademy.boldysh.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//Обработка ошибок
@ControllerAdvice
public class ControllerHelper {

    @ExceptionHandler(DaoException.class)
    public String error() {
        return "error";
    }
}
