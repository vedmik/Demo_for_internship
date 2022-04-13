package ua.coparts.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.coparts.demo.models.Car;
import ua.coparts.demo.models.User;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Iterable<Car> findByOwner(User owner);
}
