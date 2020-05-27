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

    @Autowired
    private BrandCarRepository brandCarRepository;

    @RequestMapping(value = "/add-brand", method = RequestMethod.GET)
    public String addBrand(Model model) {
        model.addAttribute("brandDto", new BrandDto());
        return "add-brand";
    }

    @RequestMapping(value = "/brand", method = RequestMethod.POST)
    public String addBrands(BrandDto brandDto) {
        brandCarService.save(new BrandCar(brandDto.getBrand()));
        return "brand";
    }

    @RequestMapping(value = "delete-brand", method = RequestMethod.GET)
    public void getBrand(Model model) {
        BrandDto brandDto = new BrandDto();
        model.addAttribute("brandDto", brandDto);
        model.addAttribute("brandCars", brandCarService.findAll());
    }

    @RequestMapping(value = "delete-brand", method = RequestMethod.POST)
    public String deleteBrand(BrandDto brandDto) {
        BrandCar brandCar = brandCarRepository.findByBrand(brandDto.getBrand());
        brandDto.setBrand(brandCar.getBrand());
        brandCarRepository.deleteBrandCarByBrand(brandDto.getBrand());
        return "brand";
    }
}
