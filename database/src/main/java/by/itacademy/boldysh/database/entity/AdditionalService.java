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
@ToString(exclude = "orderRentalCars")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Entity
@Table(name = "additional_services", schema = "car_storage")
public class AdditionalService extends BaseEntity<Long> {


    @Column(name = "services")
    @Enumerated(EnumType.STRING)
    private Services services;

    @Column(name = "cost")
    private Integer cost;

    @OneToMany(mappedBy = "additionalService")
    private List<OrderRentalCar> orderRentalCars = new ArrayList<>();

    @Version
    @Column(name = "version")
    private Long version;

    public AdditionalService(Services services, Integer cost) {
        this.services = services;
        this.cost = cost;
    }
}
