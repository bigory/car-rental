package by.itacademy.boldysh.database.dto;

import by.itacademy.boldysh.database.entity.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRentalCarDtoNew {

    String passportNumber;
    String vinNumber;
    String additionalService;
    String startRentalCar;
    String finishRentalCar;
    StatusOrder statusOrder;

}
