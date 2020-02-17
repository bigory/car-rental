package by.itacademy.boldysh.database.dto;

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
    private Integer yearOfIssue;
    private Integer costRentalOfDay;
    private String transmission;
    private String classCar;

}
