package by.itacademy.boldysh.database.dto;


import by.itacademy.boldysh.database.entity.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlackListUserServiceDto {

    private String cause;
    private UserService userService;
}
