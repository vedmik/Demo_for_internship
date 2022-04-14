package ua.coparts.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.coparts.demo.models.Car;
import ua.coparts.demo.models.User;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Iterable<Car> findByOwner(User owner);
}
