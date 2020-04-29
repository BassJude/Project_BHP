package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.File;
import java.util.Objects;

@Service
public class HomeService {

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
        //        int numberOfFiles = homeService.getNumberOfFiles(new File(PATH_TO_SLIDES));
        if (number < 1) {
            number = 1;
        }
        if (number > 37) {
            number = 37;
        }
        userService.setSlideNumberInLoggedUser(number);
        model.addAttribute("numberImage", number);
        model.addAttribute("images", 37);
    }
}
