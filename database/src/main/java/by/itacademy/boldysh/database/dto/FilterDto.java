package by.itacademy.boldysh.database.dto;

import by.itacademy.boldysh.database.entity.CarClass;
import by.itacademy.boldysh.database.entity.Transmission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterDto {

    private String brandCar;
    private String modelCar;
    private CarClass classCar;
    private Transmission transmission;
    private Integer yearOfIssue;
    private BigDecimal costRentalOfDay;
}
