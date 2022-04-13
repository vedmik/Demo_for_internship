package ua.coparts.demo.models;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;

    private String carBrand;
    private String carModel;
    private int carYear;
    private String carColor;
    private String carDesc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    public Car() {
    }

    public Car(String carBrand, String carModel, int carYear, String carColor, String carDesc, User user) {
        this.owner = user;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carDesc = carDesc;
        this.carYear = carYear;
        this.carColor = carColor;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getModel() {
        return carModel;
    }

    public void setModel(String model) {
        this.carModel = model;
    }

    public String getCarDesc() {
        return carDesc;
    }

    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }
}
