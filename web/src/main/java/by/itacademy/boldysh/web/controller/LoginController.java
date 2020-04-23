package by.itacademy.boldysh.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void showLoginPage() {
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginUserService() {
    }

    @RequestMapping(value = "/login-error", method = RequestMethod.GET)
    public String showLoginErrorPage() {
        return "login-error";
    }
}
