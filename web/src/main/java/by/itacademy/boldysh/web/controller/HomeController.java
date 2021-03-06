package by.itacademy.boldysh.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String defaultShowHomePage() {
        return "home";
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/news-sale")
    public String showNewsSale() {
        return "news-sale";
    }

    @GetMapping("/contact")
    public String showContact() {
        return "contact";
    }

    @GetMapping("/about-us")
    public String showAboutUs() {
        return "about-us";
    }

    @GetMapping("/rent-terms")
    public String showRentTerms() {
        return "rent-terms";
    }

    @GetMapping("/sign-out")
    public String showLogOut() {
        return "sign-out";
    }

    @PostMapping("/sign-out")
    public String logOut() {
        return "login";
    }
}

