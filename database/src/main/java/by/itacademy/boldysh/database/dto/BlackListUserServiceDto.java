package by.itacademy.boldysh.database.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlackListUserServiceDto {

    private Long id;
    private String cause;
    private Long userServiceId;
}
