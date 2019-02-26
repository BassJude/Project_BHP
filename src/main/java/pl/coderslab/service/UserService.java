package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.model.User;
import pl.coderslab.model.UserSession;
import pl.coderslab.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSession userSession;


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
        String login = user.getLogin();
        if (login.length() != login.replaceAll(" ", "").length()) {
            return "Login nie może mieć spacji";
        }
        if (!user.getPassword().equals(user.getPassword2())) {
            return "Hasła muszą być takie same";
        }
        return "registrationSucces";
    }

    // login
    public String checkLogin(String login, String password, Model model) {
        if (login.length() == 0) {
            model.addAttribute("loginInvalid", true);
            model.addAttribute("messageLogin", "Podaj login");
            return "Podaj login";
        }
        if (password.length() == 0) {
            model.addAttribute("passInvalid", true);
            model.addAttribute("messagePass", "Podaj hasło");
            return "Podaj hasło";
        }
        if (userRepository.countByLogin(login) == 0) {
            model.addAttribute("loginInvalid", true);
            model.addAttribute("messageLogin", "Błąd logowania, zły login lub hasło");
            return "Login niepoprawny lub nie istnieje";
        }
        if (userRepository.countByLogin(login) > 1) {
            model.addAttribute("loginInvalid", true);
            model.addAttribute("messageLogin", "Duplikacja loginów, skontaktuj się w administratorem");
            return "Duplikacja loginów, skontaktuj się w administratorem";
        }

        User user = userRepository.findUserByLogin(login);
        if (!user.getPassword().equals(password)) {
            model.addAttribute("passInvalid", true);
            model.addAttribute("messagePass", "Podaj prawidłowe hasło");
            return "Podaj prawidłowe hasło";
        }
        return "loginSucces";
    }

    // save user in session
    public void sessionStart(String login) {
        userSession.setUserInSession(userRepository.findUserByLogin(login));
// TODO testy
        userSession.setLoggedUser(true);
    }
    // edit in session
    public User getUserSession(){
        return userSession.getUserInSession();
    }




}
