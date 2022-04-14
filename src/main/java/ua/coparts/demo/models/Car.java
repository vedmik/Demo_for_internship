package ua.coparts.demo.models;

import javax.persistence.*;

@Entity
public class Car{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;

    private String carBrand;
    private String carModel;
    private int carYear;
    private String carDesc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    public Car() {
    }

    public Car(String carBrand, String carModel, int carYear, String carDesc, User user) {
        this.owner = user;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carDesc = carDesc;
        this.carYear = carYear;
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

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }


    public String getCarDesc() {
        return carDesc;
    }

    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
