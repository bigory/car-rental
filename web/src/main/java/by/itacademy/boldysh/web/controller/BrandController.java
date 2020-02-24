package by.itacademy.boldysh.web.controller;

import by.itacademy.boldysh.database.dto.BrandDto;
import by.itacademy.boldysh.database.entity.BrandCar;
import by.itacademy.boldysh.database.repository.BrandCarRepository;
import by.itacademy.boldysh.service.interfaces.BrandCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BrandController {

    @Autowired
    private BrandCarService brandCarService;

    @RequestMapping(value = "/add-brand", method = RequestMethod.GET)
    public String addBrand(Model model) {
        BrandDto brandDto = new BrandDto();
        model.addAttribute("brandDto", brandDto);
        return "add-brand";
    }

    @RequestMapping(value = "/add-brand", method = RequestMethod.POST)
    public String addBrands(BrandDto brandDto) {
        String brand = brandDto.getBrand();
        BrandCar brandCar = new BrandCar(brand);
        brandCarService.save(brandCar);
        return "brand";
    }
}
