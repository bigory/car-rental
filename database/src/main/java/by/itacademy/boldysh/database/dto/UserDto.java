package by.itacademy.boldysh.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String firstName;
    private String secondName;
    private String passportNumber;
    private String telephone;
    private String email;
    private String password;
    private String role;

}
