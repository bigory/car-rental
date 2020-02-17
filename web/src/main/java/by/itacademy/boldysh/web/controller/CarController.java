package by.itacademy.boldysh.web.controller;

import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.Transmission;
import by.itacademy.boldysh.database.repository.CarRepository;
import by.itacademy.boldysh.service.interfaces.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarController {

    @ModelAttribute("transmissions")
    public Transmission[] getTransmissions() {
        return Transmission.values();
    }

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getCar(Model model) {
        model.addAttribute("car", carRepository.findByVinNumber("XCSD2133412"));
        model.addAttribute("cars", carService.findAll());
        return "car";
    }

    @GetMapping
    public String getPage() {
        return "create-car";
    }

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public String createCar(Car car) {
        System.out.println(car);
        return "/redirect:/car";
    }
}
