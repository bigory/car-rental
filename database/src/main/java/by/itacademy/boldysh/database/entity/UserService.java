package by.itacademy.boldysh.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "blackListUserService")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@Entity
@Table(name = "user_service", schema = "car_storage")
public class UserService extends BaseEntity<Long> {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "isadmin")
    private String role;

    @OneToOne(mappedBy = "userService", cascade = CascadeType.ALL)
    private BlackListUserService blackListUserService;

    @ManyToMany
    @JoinTable(name = "order_rental_car", schema = "car_storage",
            joinColumns = @JoinColumn(name = "user_service_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars = new ArrayList<>();

    public UserService(String firstName, String secondName, String passportNumber,
                       String telephone, String email, String password, String role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.passportNumber = passportNumber;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserService(String firstName, String secondName, String passportNumber, String telephone, String email, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.passportNumber = passportNumber;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
    }
}