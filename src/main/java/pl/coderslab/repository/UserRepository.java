package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    long countByLogin(String login);

    long countBySuperUser(boolean check);

    User findUserByLogin(String login);

    List<User> findUserByLastNameContaining(String search);

    List<User> findUserByPassedEgzam(Boolean passed);

    List<User> findUsersByPassedEgzam(List<User> userList);
}
