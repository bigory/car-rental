package by.itacademy.boldysh.web.controller;

import by.itacademy.boldysh.database.dto.OrderRentalCarDto;
import by.itacademy.boldysh.database.dto.UserDto;
import by.itacademy.boldysh.database.entity.OrderRentalCar;
import by.itacademy.boldysh.database.entity.StatusOrder;
import by.itacademy.boldysh.database.entity.UserService;
import by.itacademy.boldysh.database.repository.AdditionalServiceRepository;
import by.itacademy.boldysh.database.repository.CarRepository;
import by.itacademy.boldysh.database.repository.OrderCarRentalCarRepository;
import by.itacademy.boldysh.database.repository.UserServiceRepository;
import by.itacademy.boldysh.service.interfaces.OrderRentalCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class OrderRentalCarController {

    @Autowired
    OrderCarRentalCarRepository orderCarRentalCarRepository;

    @Autowired
    OrderRentalCarService orderRentalCarService;

    @Autowired
    UserServiceRepository userServiceRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    AdditionalServiceRepository additionalServiceRepository;

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
    public void getPageParametrUpdateCar(Model model, @RequestParam(value = "id") Long id) {
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
    public String getPageDeleteCar(Model model, @RequestParam(value = "id") Long id) {
        Optional<OrderRentalCar> orderRentalCar = orderCarRentalCarRepository.findById(id);
        OrderRentalCarDto orderRentalCarDto = orderRentalCarService.conversionOrderRentalCar(orderRentalCar, id);
        model.addAttribute("orderRentalCarDto", orderRentalCarDto);

        return "delete-order";
    }

    @RequestMapping(value = "/delete-order", method = RequestMethod.POST)
    public String deleteCar(@RequestParam(value = "id") Long id) {
        orderCarRentalCarRepository.delete(orderCarRentalCarRepository.findById(id).get());
        return "info-delete";
    }


    @RequestMapping(value = "/add-order", method = RequestMethod.GET)
    public String getPageAddCars(Model model) {
        model.addAttribute("orderRentalCarDto", new OrderRentalCarDto());
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("additionalService", additionalServiceRepository.findAll());
        model.addAttribute("userDto", new UserDto());
        return "add-order";
    }


    @RequestMapping(value = "/add-order", method = RequestMethod.POST)
    public String createCar() {

        OrderRentalCar orderRentalCar = new OrderRentalCar();
        orderRentalCarService.save(orderRentalCar);
        return "order";
    }

    @RequestMapping(value = "/user-orders", method = RequestMethod.GET)
    public String getPagesOrderRentalCarUserService(Model model, @RequestParam(value = "page") Optional<Integer> page,
                                                    @RequestParam(value = "size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(3);
        UserService userService = (UserService) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<OrderRentalCarDto> pageOrdersRentalCarDto = orderRentalCarService.paginationOrdersRentalCar(PageRequest.of(currentPage - 1, pageSize),
                orderCarRentalCarRepository.findAllByUserServiceEmail(userService.getEmail()));

        model.addAttribute("pageOrdersRentalCarDto", pageOrdersRentalCarDto);

        int totalPages = pageOrdersRentalCarDto.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "user-orders";
    }

}