package by.itacademy.boldysh.database.dto;


import by.itacademy.boldysh.database.entity.AdditionalService;
import by.itacademy.boldysh.database.entity.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRentalCarDto {

    Long id;
    String firstName;
    String secondName;
    String passportNumber;
    String brandCar;
    String modelCar;
    String vinNumber;
    Integer costCar;
    AdditionalService additionalService;
    Integer costAdditionalService;
    LocalDate startRentalCar;
    LocalDate finishRentalCar;
    Integer costOrder;
    StatusOrder statusOrder;

}
