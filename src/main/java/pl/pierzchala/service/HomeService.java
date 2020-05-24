package pl.pierzchala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.pierzchala.model.User;
import pl.pierzchala.model.UserSession;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Objects;
import java.util.Properties;

@Service
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName", "admin"})
public class HomeService {

    private UserService userService;
    private UserSession userSession;

    @Autowired
    public HomeService(UserService userService, UserSession userSession) {
        this.userService = userService;
        this.userSession = userSession;
    }


    public void setSlide(int number, Model model) {
        Integer numberOfSlides = getNumberOfSlides();
        if (number < 1) {
            number = 1;
        }
        if (number > numberOfSlides) {
            number = numberOfSlides;
        }
        userService.setSlideNumberInLoggedUser(number);
        model.addAttribute("numberImage", number);
        model.addAttribute("images", numberOfSlides);
    }

    // ilość slajdów
    private Integer getNumberOfSlides() {
        File folder = null;
        try {
            Properties properties = System.getProperties();
            String property = properties.getProperty("sun.desktop");
            if ("windows".equals(property)) {
                folder = new File("./tomcat/webapps/bhp/slides");
            }
            return Objects.requireNonNull(folder.listFiles()).length;
        } catch (Exception e) {
            System.out.print("e");
            return 37;
        }
    }

    public String registrationUser(User user, BindingResult result, Model model) {

        String message = userService.checkLoginAndPassword(user);
        if (!"registrationSuccess".equals(message)) {
            model.addAttribute("invalid", true);
            model.addAttribute("message", message);
            return "/users/registration";
        }
        if (result.hasErrors()) {
            return "/users/registration";
        }

        String passToHash = user.getPassword(); // hash password
        user.setPasswordHash(passToHash);
        user.setLastSlide(1);

        userService.save(user);
        model.addAttribute("registration", true);
        model.addAttribute("message", "Dziękujemy za rejestrację. Teraz możesz się zalogować.");
        return "/home";
    }

    public String loginPost(Model model, HttpServletRequest request) {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String check = userService.checkLogin(login, pass, model);
        if (!"loginSuccess".equals(check)) {
            request.setAttribute("login", login); //, by nie musieć wpisywać znowu
            request.setAttribute("pass", pass);
            return "/login";
        }
        model.addAttribute("loggedUser", true);
        userService.sessionStart(login);

        if (userSession.getUserInSession().isSuperUser()) {
            model.addAttribute("admin", true);

        }

        model.addAttribute("firstName", userSession.getUserInSession().getFirstName());
        model.addAttribute("registration", true);
        model.addAttribute("message", "Zalogowałeś się. Zapoznaj się z materiałami szkoleniowymi, wykonaj test.");
        return "/home";
    }

    public String logout(Model model) {
        model.addAttribute("loggedUser", false);
        model.addAttribute("admin", false);
        model.addAttribute("firstName", "użytkowniku");

        userSession.setLoggedUser(false);
        userSession.setUserInSession(null);
        return "/home";
    }
}
