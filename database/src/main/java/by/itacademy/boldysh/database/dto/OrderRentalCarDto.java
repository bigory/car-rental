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

    private Long id;
    private String firstName;
    private String secondName;
    private String passportNumber;
    private String brandCar;
    private String modelCar;
    private String vinNumber;
    private BigDecimal costCar;
    private AdditionalService additionalService;
    private BigDecimal costAdditionalService;
    private LocalDate startRentalCar;
    private LocalDate finishRentalCar;
    private BigDecimal costOrder;
    private StatusOrder statusOrder;
}
