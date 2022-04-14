package ua.coparts.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.coparts.demo.models.Car;
import ua.coparts.demo.models.FuelPoint;

import java.util.List;

@Repository
public interface FuelPointRepository extends JpaRepository<FuelPoint, Long> {
    List<FuelPoint> findByCarId(Car car);
}