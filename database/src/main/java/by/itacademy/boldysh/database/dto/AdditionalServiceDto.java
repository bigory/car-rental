package by.itacademy.boldysh.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceDto {

    private String services;
    private BigDecimal cost;

}
