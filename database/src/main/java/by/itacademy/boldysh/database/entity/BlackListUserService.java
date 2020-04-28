package by.itacademy.boldysh.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Builder
@Component
@Entity
@Table(name = "black_list", schema = "car_storage")
public class BlackListUserService extends BaseEntity<Long> {

    @Column(name = "cause")
    private String cause;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserService userService;


    public BlackListUserService(String cause, UserService userService) {
        this.cause = cause;
        this.userService = userService;
    }
}
