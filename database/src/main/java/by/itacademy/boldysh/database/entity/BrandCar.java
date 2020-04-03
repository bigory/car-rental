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
@ToString(exclude = "cars")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@Entity
@Table(name = "brand_car", schema = "car_storage")
public class BrandCar extends BaseEntity<Long> {

    @Column(name = "brand")
    private String brand;

    @OneToMany(mappedBy = "brandCar", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();

    @Version
    @Column(name = "version")
    private Long version;

    public BrandCar(String brand) {
        this.brand = brand;
    }

}
