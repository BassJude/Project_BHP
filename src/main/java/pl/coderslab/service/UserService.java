package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.model.User;
import pl.coderslab.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // zapis
    public void save(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.delete(id);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    //registration
    public String checkRegistration(User user) {
        long value = userRepository.countByLogin(user.getLogin());
        if (value != 0) {
            return "Login nie jest unikatowy, podaj inny login !";
        }
        if (!user.getPassword().equals(user.getPassword2())) {
            return "Hasła muszą być takie same";
        }
        return "OK";
    }
}
