package by.itacademy.boldysh.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "userServices")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@Entity
@Table(name = "car", schema = "car_storage")
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Car extends BaseEntity<Long> {

    @Column(name = "model")
    private String model;

    @Column(name = "year_of_issue")
    private Integer yearOfIssue;

    @Column(name = "vin_number")
    private String vinNumber;

    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column(name = "class_car")
    @Enumerated(EnumType.STRING)
    private CarClass carClass;

    @Column(name = "cost_rental_of_day")
    private BigDecimal costRentalOfDay;

    @ManyToOne
    @JoinColumn(name = "brand_car_id")
    private BrandCar brandCar;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "order_rental_car", schema = "car_storage",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "user_service_id"))
    private List<UserService> userServices = new ArrayList<>();

    @Version
    @Column(name = "version")
    private Long version;

    public Car(BrandCar brandCar, String model, Integer yearOfIssue, String vinNumber, Transmission transmission, CarClass carClass, BigDecimal costRentalOfDay) {
        this.model = model;
        this.yearOfIssue = yearOfIssue;
        this.vinNumber = vinNumber;
        this.transmission = transmission;
        this.carClass = carClass;
        this.costRentalOfDay = costRentalOfDay;
        this.brandCar = brandCar;
    }
}