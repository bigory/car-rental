package by.itacademy.boldysh.web.controller;

import by.itacademy.boldysh.database.dto.CarDto;
import by.itacademy.boldysh.database.entity.*;
import by.itacademy.boldysh.database.repository.BrandCarRepository;
import by.itacademy.boldysh.database.repository.CarRepository;
import by.itacademy.boldysh.service.interfaces.BrandCarService;
import by.itacademy.boldysh.service.interfaces.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarController {

    @ModelAttribute("transmissions")
    public Transmission[] getTransmissions() {
        return Transmission.values();
    }

    @ModelAttribute("carClass")
    public CarClass[] getCarClass() {
        return CarClass.values();
    }

    @Autowired
    private CarService carService;

    @Autowired
    private BrandCarService brandCarService;

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public String getCar(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "car";
    }

    @RequestMapping(value = "/addCars", method = RequestMethod.GET)
    public String getPage(Model model) {
        CarDto carDto = new CarDto();
        model.addAttribute("carDto", carDto);
        model.addAttribute("brandCars", brandCarService.findAll());
        model.addAttribute("brandCars", brandCarService.findAll());
        model.addAttribute("carClass", CarClass.values());
        model.addAttribute("transmissions", Transmission.values());
        return "addCars";
    }

    @RequestMapping(value = "/addCars", method = RequestMethod.POST)
    public String createCar(Model model, @ModelAttribute("carDto") CarDto carDto) {

        BrandCar brandCar = carDto.getBrandCar();
        String modelCar = carDto.getModelCar();
        Integer yearOfIssue = carDto.getYearOfIssue();
        String vinNumber = carDto.getVinNumber();
        Transmission transmission = carDto.getTransmission();
        CarClass carClass = carDto.getClassCar();
        Integer costRentalOfDay = carDto.getCostRentalOfDay();

        Car car = new Car(brandCar, modelCar, yearOfIssue, vinNumber, transmission, carClass, costRentalOfDay);
        carService.save(car);
        return "redirect:car";
    }
}
