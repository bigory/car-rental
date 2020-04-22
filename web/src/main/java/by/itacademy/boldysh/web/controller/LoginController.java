package by.itacademy.boldysh.web.controller;

import by.itacademy.boldysh.service.interfaces.UserServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void showLoginPage() {
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUserService() {
        return "admin-page";
    }

    @RequestMapping(value = "/login-error", method = RequestMethod.GET)
    public String showLoginErrorPage() {
        return "login-error";
    }
}
