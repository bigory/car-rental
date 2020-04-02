package by.itacademy.boldysh.database.dto;

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
public class FilterDto {

    String brandCar;
    String modelCar;
    CarClass classCar;
    Transmission transmission;
    Integer yearOfIssue;
    Integer costRentalOfDay;
}
