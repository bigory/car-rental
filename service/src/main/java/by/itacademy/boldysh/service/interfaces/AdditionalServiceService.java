package by.itacademy.boldysh.service.interfaces;

import by.itacademy.boldysh.database.entity.AdditionalService;
import by.itacademy.boldysh.database.entity.Services;
import by.itacademy.boldysh.service.util.BaseMethod;

public interface AdditionalServiceService extends BaseMethod<Long, AdditionalService> {

    void updateAdditionalService(Services additionalService, Integer cost);

}
