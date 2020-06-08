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

    private String passportNumber;
    private String vinNumber;
    private String additionalService;
    private String startRentalCar;
    private String finishRentalCar;
    private StatusOrder statusOrder;

}
