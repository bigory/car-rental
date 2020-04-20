package by.itacademy.boldysh.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/news-sale")
    public String showNewsSale() {
        return "news-sale";
    }

    @GetMapping("/contact")
    public String showAboutUs() {
        return "contact";
    }
}
