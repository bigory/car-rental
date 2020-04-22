package by.itacademy.boldysh.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Secured({"ROLE_ADMIN", "ROLE_USER"})
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
}

