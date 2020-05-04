package by.itacademy.boldysh.web.controller;

import by.itacademy.boldysh.database.dto.OrderRentalCarDto;
import by.itacademy.boldysh.database.entity.OrderRentalCar;
import by.itacademy.boldysh.database.entity.StatusOrder;
import by.itacademy.boldysh.database.repository.AdditionalServiceRepository;
import by.itacademy.boldysh.database.repository.CarRepository;
import by.itacademy.boldysh.database.repository.OrderCarRentalCarRepository;
import by.itacademy.boldysh.database.repository.UserServiceRepository;
import by.itacademy.boldysh.service.interfaces.OrderRentalCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        Page<OrderRentalCar> pageOrdersRentalCar = orderRentalCarService.findByPaginated(PageRequest.of(currentPage - 1, pageSize),
                orderRentalCarService.findAll());

        model.addAttribute("pageOrdersRentalCar", pageOrdersRentalCar);

        int totalPages = pageOrdersRentalCar.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "orders";
    }

    @RequestMapping(value = "/update-order", method = RequestMethod.GET)
    public void getPageParametrUpdateCar(Model model, OrderRentalCarDto orderRentalCarDto, @RequestParam(value = "id") Long id) {
        Optional<OrderRentalCar> orderRentalCar = orderCarRentalCarRepository.findById(id);
        orderRentalCarDto.setFirstName(String.valueOf(userServiceRepository.findById(orderRentalCar.get().getUserServiceId()).get().getFirstName()));
        orderRentalCarDto.setSecondName(String.valueOf(userServiceRepository.findById(orderRentalCar.get().getUserServiceId()).get().getSecondName()));
        orderRentalCarDto.setPassportNumber(String.valueOf(userServiceRepository.findById(orderRentalCar.get().getUserServiceId()).get().getPassportNumber()));
        orderRentalCarDto.setBrandCar(carRepository.findById(orderRentalCar.get().getCarId()).get().getBrandCar().getBrand());
        orderRentalCarDto.setModelCar(carRepository.findById(orderRentalCar.get().getCarId()).get().getModel());
        orderRentalCarDto.setVinNumber(carRepository.findById(orderRentalCar.get().getCarId()).get().getVinNumber());
        orderRentalCarDto.setCostCar(carRepository.findById(orderRentalCar.get().getCarId()).get().getCostRentalOfDay());
        orderRentalCarDto.setAdditionalService(additionalServiceRepository.findByServices(orderRentalCar.get().getAdditionalService().getServices()));
        orderRentalCarDto.setCostAdditionalService(additionalServiceRepository.findByServices(orderRentalCar.get().getAdditionalService().getServices()).getCost());
        orderRentalCarDto.setStartRentalCar(orderRentalCar.get().getDateStartRental());
        orderRentalCarDto.setFinishRentalCar(orderRentalCar.get().getDateFinishRental());
        orderRentalCarDto.setCostOrder(orderRentalCar.get().getCost());
        orderRentalCarDto.setStatusOrder(orderRentalCar.get().getStatusOrder());

        model.addAttribute("orderRentalCar", orderRentalCar);
    }
}
