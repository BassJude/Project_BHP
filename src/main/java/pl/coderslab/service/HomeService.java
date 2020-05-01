package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.File;
import java.util.Objects;

@Service
public class HomeService {

    private static final String PATH_TO_SLIDES = "src/main/webapp/slides";
    private UserService userService;

    @Autowired
    public HomeService(UserService userService) {
        this.userService = userService;
    }

    // ilość slajdów
    public int getNumberOfFiles(File folder) {
        return Objects.requireNonNull(folder.listFiles()).length;
    }

    public void setSlide(int number, Model model) {
        int numberOfSlides = getNumberOfFiles(new File(PATH_TO_SLIDES));
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
}
