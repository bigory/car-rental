package by.itacademy.boldysh.database.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Component
@Entity
@Table(name = "order_rental_car", schema = "car_storage")
public class OrderRentalCar extends BaseEntity<Long> {

    @Column(name = "user_service_id")
    private Long userServiceId;

    @Column(name = "car_id")
    private Long carId;

    @Column(name = "date_start_rental")
    private LocalDate dateStartRental;

    @Column(name = "date_finish_rental")
    private LocalDate dateFinishRental;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "status_order")
    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    @ManyToOne
    @JoinColumn(name = "additional_services_id")
    private AdditionalService additionalService;

    public OrderRentalCar(Long userServiceId, Long carId, LocalDate dateStartRental, LocalDate dateFinishRental, Integer cost, StatusOrder statusOrder, AdditionalService additionalService) {
        this.userServiceId = userServiceId;
        this.carId = carId;
        this.dateStartRental = dateStartRental;
        this.dateFinishRental = dateFinishRental;
        this.cost = cost;
        this.statusOrder = statusOrder;
        this.additionalService = additionalService;
    }
}
