package pl.pierzchala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pierzchala.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    long countByLogin(String login);

    long countBySuperUser(boolean check);

    User findUserByLogin(String login);

    List<User> findUserByLastNameContaining(String search);

    List<User> findUserByPassedEgzam(Boolean passed);

    List<User> findUsersByPassedEgzam(List<User> userList);

    List<User> findUserByLastNameContainingOrFirstNameContainingOrLoginContaining(String lastName, String firstName, String login);
}
