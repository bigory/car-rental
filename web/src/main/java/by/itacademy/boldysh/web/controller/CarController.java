package by.itacademy.boldysh.web.controller;

import by.itacademy.boldysh.database.dto.CarDto;
import by.itacademy.boldysh.database.dto.FilterDto;
import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.CarClass;
import by.itacademy.boldysh.database.entity.Transmission;
import by.itacademy.boldysh.database.repository.BrandCarRepository;
import by.itacademy.boldysh.database.repository.CarRepository;
import by.itacademy.boldysh.service.interfaces.BrandCarService;
import by.itacademy.boldysh.service.interfaces.CarService;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarController {

    @ModelAttribute("filterDto")
    public FilterDto getFilterDto() {
        return new FilterDto();
    }

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
    private BrandCarService brandCarService;

    @Autowired
    private BrandCarRepository brandCarRepository;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String getPagesCars(Model model, @RequestParam(value = "page") Optional<Integer> page,
                               @RequestParam(value = "size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(3);

        Page<Car> pageCars = carService.findByPaginated(PageRequest.of(currentPage - 1, pageSize),
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
        BigDecimal costRentalOfDay = carDto.getCostRentalOfDay();

        Car car = new Car(brandCarRepository.findByBrand(brand), modelCar, yearOfIssue, vinNumber, transmission, carClass, costRentalOfDay);
        carService.save(car);
        return "car";
    }

    @RequestMapping(value = "/delete-car", method = RequestMethod.GET)
    public String getPageDeleteCar(Model model) {
        CarDto carDto = new CarDto();
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("carDto", carDto);
        return "delete-car";
    }

    @RequestMapping(value = "/delete-car", method = RequestMethod.POST)
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
    public void getFilterCar(Model model, FilterDto filterDto) {
        model.addAttribute("filterDto", filterDto);
        model.addAttribute("brandCars", brandCarService.findAll());
        model.addAttribute("modelCar", filterDto.getModelCar());
        model.addAttribute("yearOfIssue", filterDto.getYearOfIssue());
        model.addAttribute("transmissions", Transmission.values());
        model.addAttribute("carClass", CarClass.values());
        model.addAttribute("costRentalOfDay", filterDto.getCostRentalOfDay());
    }

    @RequestMapping(value = "filter-car-result", method = RequestMethod.GET)
    public void getPagesFilterCar(Model model, @ModelAttribute("filterDto") FilterDto filterDto,
                                  @RequestParam(value = "page", required = false) Optional<Integer> page,
                                  @RequestParam(value = "size", required = false) Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(2);

        Page<Car> pageCars = carService.findByFilterAndPaginationCars(filterDto, PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("pageCars", pageCars);
        int totalPages = pageCars.getTotalPages();
        List<Integer> pageNumbers;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }

    @RequestMapping(value = "/list-car-update", method = RequestMethod.GET)
    public void getUpdateCar(Model model, Car car) {
        model.addAttribute("car", car);
        model.addAttribute("cars", carRepository.findAll());
    }

    @RequestMapping(value = "/update-car", method = RequestMethod.GET)
    public void getPageParametrUpdateCar(Model model, CarDto carDto, @RequestParam(value = "id") Long id) {
        Optional<Car> car = carRepository.findById(id);
        carDto.setBrandCar(car.get().getBrandCar().getBrand());
        carDto.setModelCar(car.get().getModel());
        carDto.setVinNumber(car.get().getVinNumber());
        carDto.setYearOfIssue(car.get().getYearOfIssue());
        carDto.setCostRentalOfDay(car.get().getCostRentalOfDay());
        model.addAttribute("carDto", carDto);
    }

    @RequestMapping(value = "/update-car", method = RequestMethod.POST)
    public String getCarUpdateCar(Model model, CarDto carDto, BigDecimal costRentalOfDay, @RequestParam(value = "vinNumber") String vinNumber) {
        Car car = carRepository.findByVinNumber(vinNumber);
        carRepository.update(costRentalOfDay, car.getId());
        carDto.setBrandCar(car.getBrandCar().getBrand());
        carDto.setModelCar(car.getModel());
        carDto.setVinNumber(car.getVinNumber());
        carDto.setYearOfIssue(car.getYearOfIssue());
        Car carUpdate = carRepository.findByVinNumber(vinNumber);
        carDto.setCostRentalOfDay(carUpdate.getCostRentalOfDay());
        model.addAttribute("carDto", carDto);
        return "car";
    }
}