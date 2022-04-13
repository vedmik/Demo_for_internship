package ua.coparts.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.coparts.demo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
