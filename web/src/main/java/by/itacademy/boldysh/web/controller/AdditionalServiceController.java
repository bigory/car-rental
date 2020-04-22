package by.itacademy.boldysh.web.controller;

import by.itacademy.boldysh.database.entity.AdditionalService;
import by.itacademy.boldysh.database.repository.AdditionalServiceRepository;
import by.itacademy.boldysh.service.interfaces.AdditionalServiceService;
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
public class AdditionalServiceController {

    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;


    @Autowired
    private AdditionalServiceService additionalServiceService;


    @RequestMapping(value = "/additional-services", method = RequestMethod.GET)
    public String getPagesAdditionalService(Model model, @RequestParam(value = "page") Optional<Integer> page,
                                            @RequestParam(value = "size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(3);

        Page<AdditionalService> additionalServicePage = additionalServiceService.findByPaginated(PageRequest.of(currentPage - 1, pageSize),
                additionalServiceService.findAll());

        model.addAttribute("additionalServicesPage", additionalServicePage);

        int totalPages = additionalServicePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "additional-services";
    }
}
