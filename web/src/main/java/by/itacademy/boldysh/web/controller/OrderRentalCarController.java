package by.itacademy.boldysh.web.controller;

import by.itacademy.boldysh.database.dto.OrderRentalCarDto;
import by.itacademy.boldysh.database.dto.OrderRentalCarDtoNew;
import by.itacademy.boldysh.database.dto.UserDto;
import by.itacademy.boldysh.database.entity.AdditionalService;
import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.OrderRentalCar;
import by.itacademy.boldysh.database.entity.Services;
import by.itacademy.boldysh.database.entity.StatusOrder;
import by.itacademy.boldysh.database.entity.UserService;
import by.itacademy.boldysh.database.repository.AdditionalServiceRepository;
import by.itacademy.boldysh.database.repository.BlackListUserServiceRepository;
import by.itacademy.boldysh.database.repository.CarRepository;
import by.itacademy.boldysh.database.repository.OrderCarRentalCarRepository;
import by.itacademy.boldysh.database.repository.UserServiceRepository;
import by.itacademy.boldysh.service.interfaces.CarService;
import by.itacademy.boldysh.service.interfaces.OrderRentalCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderRentalCarController {

    @Autowired
    private OrderCarRentalCarRepository orderCarRentalCarRepository;

    @Autowired
    private OrderRentalCarService orderRentalCarService;

    @Autowired
    private UserServiceRepository userServiceRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private BlackListUserServiceRepository blackListUserServiceRepository;

    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;

    @ModelAttribute
    public StatusOrder[] getStatusOrder() {
        return StatusOrder.values();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getPagesOrderRentalCar(Model model, @RequestParam(value = "page") Optional<Integer> page,
                                         @RequestParam(value = "size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(3);

        Page<OrderRentalCarDto> pageOrdersRentalCarDto = orderRentalCarService.paginationOrdersRentalCar(PageRequest.of(currentPage - 1, pageSize),
                orderRentalCarService.findAll());

        model.addAttribute("pageOrdersRentalCarDto", pageOrdersRentalCarDto);

        int totalPages = pageOrdersRentalCarDto.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "orders";
    }


    @RequestMapping(value = "/update-order", method = RequestMethod.GET)
    public void getPageParametrUpdateOrderRentalCar(Model model, @RequestParam(value = "id") Long id) {
        Optional<OrderRentalCar> orderRentalCar = orderCarRentalCarRepository.findById(id);
        OrderRentalCarDto orderRentalCarDto = orderRentalCarService.conversionOrderRentalCar(orderRentalCar, id);
        model.addAttribute("statusOrder", StatusOrder.values());
        model.addAttribute("orderRentalCarDto", orderRentalCarDto);
    }

    @RequestMapping(value = "/update-order", method = RequestMethod.POST)
    public String getUpdateOrderRentalCar(Model model, @RequestParam(value = "id") Long id, StatusOrder
            statusOrder) {
        orderCarRentalCarRepository.update(statusOrder, id);
        OrderRentalCarDto orderRentalCarDto = orderRentalCarService.conversionOrderRentalCar(orderCarRentalCarRepository.findById(id), id);
        model.addAttribute("orderRentalCarDto", orderRentalCarDto);
        return "order";
    }

    @RequestMapping(value = "/delete-order", method = RequestMethod.GET)
    public String getPageOrderRentalCar(Model model, @RequestParam(value = "id") Long id) {
        Optional<OrderRentalCar> orderRentalCar = orderCarRentalCarRepository.findById(id);
        OrderRentalCarDto orderRentalCarDto = orderRentalCarService.conversionOrderRentalCar(orderRentalCar, id);
        model.addAttribute("orderRentalCarDto", orderRentalCarDto);

        return "delete-order";
    }

    @RequestMapping(value = "/delete-order", method = RequestMethod.POST)
    public String deleteOrderRentalCar(@RequestParam(value = "id") Long id) {
        orderCarRentalCarRepository.deleteOrderRentalCarByUserServiceIdAndCarId(orderCarRentalCarRepository.findById(id).get().getUserServiceId(),
                orderCarRentalCarRepository.findById(id).get().getCarId());
        return "info-delete";
    }

    @RequestMapping(value = "/add-order", method = RequestMethod.GET)
    public String getPageAddCars(Model model) {
        List<Car> carList = (List<Car>) carRepository.findAll();
        List<OrderRentalCar> ordersRentalCar = (List<OrderRentalCar>) orderCarRentalCarRepository.findAll();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserService userService = userServiceRepository.findByEmail(user.getUsername());
        List<Car> cars = carService.findByCarNoOrderRental(carList, ordersRentalCar);
        if (!(blackListUserServiceRepository.findByUserService(userService) == null)) {
            model.addAttribute("cause", blackListUserServiceRepository.findByUserServiceId(userService.getId()).getCause());
            return "info-cause-add-blacklist";
        }
        if (cars.size() == 0) {
            model.addAttribute("userService", userService);
            return "no-car-order";
        }
        model.addAttribute("orderRentalCarDtoNew", new OrderRentalCarDtoNew());
        model.addAttribute("cars", cars);
        model.addAttribute("additionalServices", additionalServiceRepository.findAll());
        model.addAttribute("defaultService", Services.NO);
        UserDto userDto = UserDto.builder()
                .firstName(userService.getFirstName())
                .secondName(userService.getSecondName())
                .email(userService.getEmail())
                .passportNumber(userService.getPassportNumber())
                .telephone(userService.getTelephone())
                .build();
        model.addAttribute("userDto", userDto);
        model.addAttribute("statusOrder", StatusOrder.ACCEPTED);
        model.addAttribute("localDate", LocalDate.now());
        return "add-order";
    }


    @RequestMapping(value = "/add-order", method = RequestMethod.POST)
    public String createOrderRentalCar(Model model, OrderRentalCarDtoNew orderRentalCarDtoNew, @RequestParam("startRentalCar")
            String startRentalCar, @RequestParam("finishRentalCar") String finishRentalCar) {

        Car car = carRepository.findByVinNumber(orderRentalCarDtoNew.getVinNumber());
        AdditionalService additionalService = additionalServiceRepository.findByServices(Services.valueOf(orderRentalCarDtoNew.getAdditionalService()));
        UserService userService = userServiceRepository.findByPassportNumber(orderRentalCarDtoNew.getPassportNumber());

        OrderRentalCar orderRentalCar = new OrderRentalCar();
        orderRentalCar.setUserServiceId(userService.getId());
        orderRentalCar.setCarId(car.getId());
        orderRentalCar.setAdditionalService(additionalService);
        orderRentalCar.setDateStartRental(LocalDate.parse(startRentalCar));
        orderRentalCar.setDateFinishRental(LocalDate.parse(finishRentalCar));
        orderRentalCar.setStatusOrder(orderRentalCarDtoNew.getStatusOrder());
        orderRentalCar.setCost(car.getCostRentalOfDay().add(additionalService.getCost()));
        OrderRentalCarDto orderRentalCarDto = OrderRentalCarDto.builder()
                .firstName(userService.getFirstName())
                .secondName(userService.getSecondName())
                .passportNumber(userService.getPassportNumber())
                .brandCar(car.getBrandCar().getBrand())
                .modelCar(car.getModel())
                .vinNumber(car.getVinNumber())
                .costCar(car.getCostRentalOfDay())
                .services(additionalService.getServices())
                .costAdditionalService(additionalService.getCost())
                .startRentalCar(LocalDate.parse(orderRentalCarDtoNew.getStartRentalCar()))
                .finishRentalCar(LocalDate.parse(orderRentalCarDtoNew.getFinishRentalCar()))
                .costOrder(car.getCostRentalOfDay().add(additionalService.getCost()))
                .statusOrder(orderRentalCarDtoNew.getStatusOrder())
                .build();
        model.addAttribute(orderRentalCarDto);
        orderRentalCarService.save(orderRentalCar);
        return "order";
    }

    @RequestMapping(value = "/user-orders", method = RequestMethod.GET)
    public String getPagesOrderRentalCarUserService(Model model, @RequestParam(value = "page") Optional<Integer> page,
                                                    @RequestParam(value = "size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(3);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserService userService = userServiceRepository.findByEmail(user.getUsername());
        Page<OrderRentalCarDto> pageOrdersRentalCarDto = orderRentalCarService.paginationOrdersRentalCar(PageRequest.of(currentPage - 1, pageSize),
                orderCarRentalCarRepository.findAllByUserServiceId(userService.getId()));

        model.addAttribute("pageOrdersRentalCarDto", pageOrdersRentalCarDto);

        int totalPages = pageOrdersRentalCarDto.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "user-orders";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public void getOrderRentalCarUserService(Model model, @RequestParam(value = "id") Long id) {
        Optional<OrderRentalCar> orderRentalCar = orderCarRentalCarRepository.findById(id);
        OrderRentalCarDto orderRentalCarDto = orderRentalCarService.conversionOrderRentalCar(orderRentalCar, id);
        model.addAttribute("orderRentalCarDto", orderRentalCarDto);
    }


}