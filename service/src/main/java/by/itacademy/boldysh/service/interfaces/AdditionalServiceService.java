package by.itacademy.boldysh.service.interfaces;

import by.itacademy.boldysh.database.entity.AdditionalService;
import by.itacademy.boldysh.database.entity.Services;
import by.itacademy.boldysh.service.util.BaseMethod;

import java.math.BigDecimal;

public interface AdditionalServiceService extends BaseMethod<Long, AdditionalService> {

    void updateAdditionalService(Services additionalService, BigDecimal cost);

}
