package by.itacademy.boldysh.database.dto;

import by.itacademy.boldysh.database.entity.BrandCar;
import by.itacademy.boldysh.database.entity.CarClass;
import by.itacademy.boldysh.database.entity.Transmission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {

    private String modelCar;
    private String brandCar;
    private String vinNumber;
    private Integer yearOfIssue;
    private Integer costRentalOfDay;
    private Transmission transmission;
    private CarClass classCar;

}
