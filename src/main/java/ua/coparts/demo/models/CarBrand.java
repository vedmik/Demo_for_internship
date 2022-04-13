package ua.coparts.demo.models;

import javax.persistence.*;

@Entity
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carBrandId;

    private String carBrand;

    public CarBrand() {
    }

    public CarBrand( String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public Long getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Long carBrandId) {
        this.carBrandId = carBrandId;
    }
}
