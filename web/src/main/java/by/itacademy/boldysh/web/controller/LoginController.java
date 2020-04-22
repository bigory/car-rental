package by.itacademy.boldysh.web.controller;

import by.itacademy.boldysh.service.interfaces.UserServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Secured({"ROLE_ADMIN", "ROLE_USER"})
public class LoginController {

    @Autowired
    private UserServiceService userServiceService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void showLoginPage() {
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUserService() {
        return "admin-page";
    }

    @GetMapping("/login-error")
    public String showLoginErrorPage() {
        return "login-error";
    }
}
