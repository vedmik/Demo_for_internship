package ua.coparts.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.coparts.demo.models.CarBrand;

@Repository
public interface CarBrandRepository extends CrudRepository<CarBrand, Long>{
}
