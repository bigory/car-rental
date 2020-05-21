package by.itacademy.boldysh.database.dto;


import by.itacademy.boldysh.database.entity.AdditionalService;
import by.itacademy.boldysh.database.entity.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    BigDecimal costCar;
    AdditionalService additionalService;
    BigDecimal costAdditionalService;
    LocalDate startRentalCar;
    LocalDate finishRentalCar;
    BigDecimal costOrder;
    StatusOrder statusOrder;
}