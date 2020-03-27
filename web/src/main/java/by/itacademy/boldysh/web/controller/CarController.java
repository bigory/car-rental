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
import org.apache.log4j.Logger;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarController {

    private static final Logger logger = Logger.getLogger(CarController.class);

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
    public String getPagesCar(Model model, @RequestParam(value = "page") Optional<Integer> page,
                              @RequestParam(value = "size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(3);

        Page<Car> pageCars = customFilterAndPaginationCars.findByPaginated(PageRequest.of(currentPage - 1, pageSize),
                carService.findAll());

        model.addAttribute("pageCars", pageCars);

        int totalPages = pageCars.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
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

    @RequestMapping(value = "/filter-car", method = RequestMethod.GET)
    public String getFilterCar(Model model) {
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

    @RequestMapping(value = "/filter-car-result", method = RequestMethod.GET)
    public String getPagesFilterCar(Model model, @RequestParam(value = "brandCar") String brandCar,
                                    @RequestParam(value = "modelCar") String modelCar, @RequestParam(value = "classCar") CarClass classCar,
                                    @RequestParam(value = "yearOfIssue") Integer yearOfIssue, @RequestParam(value = "transmission", required = false) Transmission transmission,
                                    @RequestParam(value = "costRentalOfDay") Integer costRentalOfDay, @RequestParam(value = "page", required = false) Optional<Integer> page,
                                    @RequestParam(value = "size", required = false) Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(2);

        Page<Car> pageCars = customFilterAndPaginationCars.findByFilterAndPaginationCars(brandCar, modelCar, yearOfIssue, transmission, classCar,
                costRentalOfDay, PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("pageCars", pageCars);

        int totalPages = pageCars.getTotalPages();
        List<Integer> pageNumbers;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "filter-car-result";
    }
}