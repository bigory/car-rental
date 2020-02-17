package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.BrandCar;
import by.itacademy.boldysh.database.repository.BrandCarRepository;
import by.itacademy.boldysh.service.interfaces.BrandCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class BrandCarServiceImpl implements BrandCarService {

    private final BrandCarRepository brandCarRepository;

    @Autowired
    public BrandCarServiceImpl(BrandCarRepository brandCarRepository) {
        this.brandCarRepository = brandCarRepository;
    }

    @Override
    public void save(BrandCar brandCar) {
        brandCarRepository.save(brandCar);
    }

    @Override
    public List<BrandCar> findAll() {
        return StreamSupport.stream(brandCarRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void delete(BrandCar brandCar) {
        brandCarRepository.delete(brandCar);
    }
}
