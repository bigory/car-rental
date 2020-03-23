package by.itacademy.boldysh.web.controller;

import by.itacademy.boldysh.database.dto.CarDto;
import by.itacademy.boldysh.database.dto.FilterDto;
import by.itacademy.boldysh.database.entity.BrandCar;
import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.CarClass;
import by.itacademy.boldysh.database.entity.Transmission;
import by.itacademy.boldysh.database.repository.BrandCarRepository;
import by.itacademy.boldysh.database.repository.CarRepository;
import by.itacademy.boldysh.service.interfaces.BrandCarService;
import by.itacademy.boldysh.service.interfaces.CarService;
import by.itacademy.boldysh.service.interfaces.CustomFilterAndPaginationCars;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private CustomFilterAndPaginationCars customFilterAndPaginationCars;

    @Autowired
    private BrandCarService brandCarService;

    @Autowired
    BrandCarRepository brandCarRepository;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String getCars(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "cars";
    }

    @RequestMapping(value = "/add-cars", method = RequestMethod.GET)
    public String getPageAddCars(Model model) {
        CarDto carDto = new CarDto();
        model.addAttribute("carDto", carDto);
        model.addAttribute("brandCars", brandCarService.findAll());
        model.addAttribute("carClass", CarClass.values());
        model.addAttribute("transmissions", Transmission.values());
        return "add-cars";
    }

    @RequestMapping(value = "/add-cars", method = RequestMethod.POST)
    public String createCar(CarDto carDto) {

        String brand = carDto.getBrandCar();
        String modelCar = carDto.getModelCar();
        Integer yearOfIssue = carDto.getYearOfIssue();
        String vinNumber = carDto.getVinNumber();
        Transmission transmission = carDto.getTransmission();
        CarClass carClass = carDto.getClassCar();
        Integer costRentalOfDay = carDto.getCostRentalOfDay();

        BrandCar brandCar = brandCarRepository.findByBrand(brand);
        Car car = new Car(brandCar, modelCar, yearOfIssue, vinNumber, transmission, carClass, costRentalOfDay);
        carService.save(car);
        return "car";
    }

    @RequestMapping(value = "/delete-cars", method = RequestMethod.GET)
    public String getPageDeleteCar(Model model) {
        CarDto carDto = new CarDto();
        model.addAttribute("carDto", carDto);
        model.addAttribute("vinNumber", carDto.getVinNumber());
        return "delete-cars";
    }

    @RequestMapping(value = "/delete-cars", method = RequestMethod.POST)
    public String deleteCar(CarDto carDto) {
        Car car = carRepository.findByVinNumber(carDto.getVinNumber());
        carDto.setBrandCar(car.getBrandCar().getBrand());
        carDto.setClassCar(car.getCarClass());
        carDto.setCostRentalOfDay(car.getCostRentalOfDay());
        carDto.setModelCar(car.getModel());
        carDto.setYearOfIssue(car.getYearOfIssue());
        carRepository.deleteByVinNumber(carDto.getVinNumber());
        return "car";
    }

    @RequestMapping(value = "filter-car", method = RequestMethod.GET)
    public String getPageFilterCar(Model model) {
        FilterDto filterDto = new FilterDto();

        model.addAttribute("filterDto", filterDto);
        model.addAttribute("brandCars", brandCarService.findAll());
        model.addAttribute("modelCar", filterDto.getModelCar());
        model.addAttribute("yearOfIssue", filterDto.getYearOfIssue());
        model.addAttribute("transmissions", Transmission.values());
        model.addAttribute("carClass", CarClass.values());
        model.addAttribute("costRentalOfDay", filterDto.getCostRentalOfDay());
        return "filter-car";
    }

    @RequestMapping(value = "/filter-car", method = RequestMethod.POST)
    public String filterCar(FilterDto filterDto, Model model,
                            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                            @RequestParam(value = "size", required = false, defaultValue = "2") Integer size) {
        model.addAttribute("cars", customFilterAndPaginationCars.findByFilterCars(filterDto.getBrandCar(),
                filterDto.getModelCar(), filterDto.getYearOfIssue(),
                filterDto.getTransmission(), filterDto.getClassCar(), filterDto.getCostRentalOfDay()));
        Page<Car> pageCars = customFilterAndPaginationCars.findByPaginated(PageRequest.of(page, size),
                customFilterAndPaginationCars.findByFilterCars(filterDto.getBrandCar(),
                        filterDto.getModelCar(), filterDto.getYearOfIssue(),
                        filterDto.getTransmission(), filterDto.getClassCar(), filterDto.getCostRentalOfDay()));
        model.addAttribute("pageCars", pageCars);
        model.addAttribute("numbers", IntStream.range(0, pageCars.getTotalPages()).toArray());
        return "filter-car-result";
    }

    /*@RequestMapping(value = "/filter-car-result", method = RequestMethod.GET)
    public String listCar(Model model, FilterDto filterDto,
                          @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                          @RequestParam(value = "size", required = false, defaultValue = "2") Integer size) {

        return "filter-car-result";*/
}
