package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    long countByLogin(String login);
    User findUserByLogin(String login);
}
