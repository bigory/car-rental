package by.itacademy.boldysh.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin-page")
    public String showAdminPage() {
        return "admin-page";
    }
}
