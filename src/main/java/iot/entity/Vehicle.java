package iot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int bus;
    private int car;
    private int motorbike;
    private int truck;

    public Vehicle(int busCount, int carCount, int motorbikeCount, int truckCount) {
        this.bus = busCount;
        this.car = carCount;
        this.motorbike = motorbikeCount;
        this.truck = truckCount;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "bus=" + bus +
                ", id=" + id +
                ", car=" + car +
                ", motorbike=" + motorbike +
                ", truck=" + truck +
                '}';
    }
}
