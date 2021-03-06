package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.AdditionalService;
import by.itacademy.boldysh.database.entity.Services;
import by.itacademy.boldysh.database.repository.AdditionalServiceRepository;
import by.itacademy.boldysh.service.interfaces.AdditionalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class AdditionalServiceImpl implements AdditionalServiceService {

    private final AdditionalServiceRepository additionalServiceRepository;

    @Autowired
    public AdditionalServiceImpl(AdditionalServiceRepository additionalServiceRepository) {
        this.additionalServiceRepository = additionalServiceRepository;
    }

    @Override
    public void save(AdditionalService additionalService) {
        additionalServiceRepository.save(additionalService);
    }

    @Override
    @Cacheable("allAdditionalServices")
    public List<AdditionalService> findAll() {
        return StreamSupport.stream(additionalServiceRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void delete(AdditionalService additionalService) {
        additionalServiceRepository.delete(additionalService);
    }

    @Override
    public Page<AdditionalService> findByPaginated(Pageable pageable, List<AdditionalService> additionalServices) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<AdditionalService> listAdditionalServices;

        if (additionalServices.size() < startItem) {
            listAdditionalServices = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, additionalServices.size());
            listAdditionalServices = additionalServices.subList(startItem, toIndex);
        }

        Page<AdditionalService> additionalServicesPage = new PageImpl<AdditionalService>(listAdditionalServices, PageRequest.of(currentPage, pageSize),
                additionalServices.size());

        return additionalServicesPage;
    }

    @Override
    public void updateAdditionalService(Services additionalService, BigDecimal cost) {
        AdditionalService additionalServices = additionalServiceRepository.findByServices(additionalService);
        additionalServices.setCost(cost);
        additionalServiceRepository.save(additionalServices);
    }
}
