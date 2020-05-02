package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.File;

@Service
public class HomeService {

    private static final String PATH_TO_SLIDES = "src/main/webapp/slides";
    private UserService userService;

    @Autowired
    public HomeService(UserService userService) {
        this.userService = userService;
    }

    // ilość slajdów
    public Integer getNumberOfFiles(File folder) {
        return folder.listFiles().length;
    }

    public void setSlide(int number, Model model) {
        Integer numberOfSlides = getNumberOfFiles(new File(PATH_TO_SLIDES));
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
