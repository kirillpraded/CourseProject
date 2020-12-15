package by.praded.repository;

import by.praded.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kiryl Praded
 * 25.11.2020
 */
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
