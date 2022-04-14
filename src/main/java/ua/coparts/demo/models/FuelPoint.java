package ua.coparts.demo.models;

import javax.persistence.*;

@Entity
public class FuelPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private int odometer;
    private double refueling;
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car carId;

    public FuelPoint() {
    }

    public FuelPoint(int odometer, double refueling, double price, Car carId) {
        this.odometer = odometer;
        this.refueling = refueling;
        this.price = price;
        this.carId = carId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getRefueling() {
        return refueling;
    }

    public void setRefueling(double refueling) {
        this.refueling = refueling;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Car getCarId() {
        return carId;
    }

    public void setCarId(Car carId) {
        this.carId = carId;
    }

}
