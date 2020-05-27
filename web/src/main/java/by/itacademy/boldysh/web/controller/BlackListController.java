package by.itacademy.boldysh.web.controller;


import by.itacademy.boldysh.database.entity.BlackListUserService;
import by.itacademy.boldysh.database.entity.BlackListUserService_;
import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.repository.BlackListUserServiceRepository;
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

import javax.servlet.http.PushBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BlackListController {

    @Autowired
    BlackListUserServiceService blackListUserServiceService;


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
}


