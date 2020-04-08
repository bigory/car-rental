package by.itacademy.boldysh.web.controller;

import by.itacademy.boldysh.database.dto.UserDto;
import by.itacademy.boldysh.database.entity.UserService;
import by.itacademy.boldysh.database.repository.UserServiceRepository;
import by.itacademy.boldysh.service.interfaces.UserServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceController {

    @Autowired
    private UserServiceService userServiceService;

    @Autowired
    private UserServiceRepository userServiceRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String getPageUser(Model model, @RequestParam(value = "page") Optional<Integer> page,
                              @RequestParam(value = "size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(2);

        Page<UserService> pagesUsers = userServiceService.findByPaginated(PageRequest.of(currentPage - 1, pageSize), userServiceService.findAll());

        model.addAttribute("pagesUsers", pagesUsers);

        int totalPages = pagesUsers.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "users";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public void getAddUserServices(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String createCar(UserDto userDto) {

        UserService userServiceRentalCar = new UserService(userDto.getFirstName(), userDto.getSecondName(), userDto.getPassportNumber(),
                userDto.getTelephone(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()));
        userServiceService.save(userServiceRentalCar);
        return "user";
    }

    @RequestMapping(value = "/delete-user", method = RequestMethod.GET)
    public String getPageDeleteCar(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "delete-user";
    }

    @RequestMapping(value = "/delete-user", method = RequestMethod.POST)
    public String deleteCar(UserDto userDto) {
        UserService userServiceRentalCar = userServiceRepository.findByPassportNumber(userDto.getPassportNumber());
        userDto.setFirstName(userServiceRentalCar.getFirstName());
        userDto.setSecondName(userServiceRentalCar.getSecondName());
        userDto.setPassportNumber(userServiceRentalCar.getPassportNumber());
        userDto.setTelephone(userServiceRentalCar.getTelephone());
        userDto.setEmail(userServiceRentalCar.getEmail());
        userServiceRepository.delete(userServiceRentalCar);
        return "user";
    }
}
