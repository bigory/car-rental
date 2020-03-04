package by.itacademy.boldysh.database.dto;

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
    String model;
    String classCar;
    String transmission;
    Integer yearOfIssue;
    Integer costRentalOfDay;
}
