package by.itacademy.boldysh.web.controller;


import by.itacademy.boldysh.database.dto.BlackListUserServiceDto;
import by.itacademy.boldysh.database.dto.UserDto;
import by.itacademy.boldysh.database.entity.BlackListUserService;
import by.itacademy.boldysh.database.entity.UserService;
import by.itacademy.boldysh.database.repository.BlackListUserServiceRepository;
import by.itacademy.boldysh.database.repository.UserServiceRepository;
import by.itacademy.boldysh.service.interfaces.BlackListUserServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class BlackListController {

    @Autowired
    BlackListUserServiceService blackListUserServiceService;

    @Autowired
    BlackListUserServiceRepository blackListUserServiceRepository;

    @Autowired
    UserServiceRepository userServiceRepository;


    @RequestMapping(value = "black-list-user", method = RequestMethod.GET)
    public String getBlackList(Model model, @RequestParam(value = "page") Optional<Integer> page,
                               @RequestParam(value = "size") Optional<Integer> size) {

        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(3);

        Page<BlackListUserService> pageBlackListUser = blackListUserServiceService.findByPaginated(PageRequest.of(currentPage - 1, pageSize),
                blackListUserServiceService.findAll());

        model.addAttribute("pageBlackListUser", pageBlackListUser);

        int totalPages = pageBlackListUser.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "black-list-user";
    }

    @RequestMapping(value = "add-user-blacklist", method = RequestMethod.GET)
    public String getPageAddUserBlackList(Model model) {
        model.addAttribute("blackListUserServiceDto", new BlackListUserServiceDto());
        model.addAttribute("userServices", userServiceRepository.findAll());
        return "add-user-blacklist";
    }

    @RequestMapping(value = "add-user-blacklist", method = RequestMethod.POST)
    public String addUserBlackList(BlackListUserServiceDto blackListUserServiceDto, Model model) {

        UserService userService = userServiceRepository.findById(blackListUserServiceDto.getUserServiceId()).get();
        BlackListUserService blackListUserService = BlackListUserService.builder()
                .userService(userService)
                .cause(blackListUserServiceDto.getCause())
                .build();
        blackListUserServiceService.save(blackListUserService);
        model.addAttribute("blackListUserServiceDto", blackListUserServiceDto);
        model.addAttribute("userService", userService);
        return "info-blacklist";
    }

    @RequestMapping(value = "/delete-user-blacklist", method = RequestMethod.GET)
    public String getPageDeleteCar(Model model, @RequestParam(value = "id") Long id) {
        Optional<BlackListUserService> blackListUserService = blackListUserServiceRepository.findById(id);
        Optional<UserService> userService = userServiceRepository.findById(blackListUserService.get().getUserService().getId());
        UserDto userDto = UserDto.builder()
                .firstName(userService.get().getFirstName())
                .secondName(userService.get().getSecondName())
                .passportNumber(userService.get().getPassportNumber())
                .build();
        BlackListUserServiceDto blackListUserServiceDto = BlackListUserServiceDto.builder()
                .id(id)
                .userServiceId(blackListUserService.get().getUserService().getId())
                .cause(blackListUserService.get().getCause())
                .build();
        model.addAttribute("blackListUserServiceDto", blackListUserServiceDto);
        model.addAttribute("userDto", userDto);
        return "/delete-user-blacklist";
    }

    @RequestMapping(value = "/delete-user-blacklist", method = RequestMethod.POST)
    public String deleteCar(@RequestParam(value = "id") Long id) {
        blackListUserServiceRepository.delete(blackListUserServiceRepository.findById(id).get());
        return "info-delete-user-black";
    }

}


