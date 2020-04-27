package pl.coderslab.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.utils.BCrypt;
import pl.coderslab.validator.EditValidator;
import pl.coderslab.validator.RegistrationValidator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    @NotBlank(groups = {RegistrationValidator.class}, message = "Podaj login")
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class})
    private String login;

    @Column(length = 30, nullable = false, columnDefinition = "VARCHAR(255) NOT NULL")
    @Size(min = 5, max = 30, message = "Hasło musi miec od 5 do 30 znaków", groups = RegistrationValidator.class)
    @NotBlank(groups = RegistrationValidator.class)
    private String password;

    @Transient
    @Size(min = 5, max = 30, message = "Hasło musi miec od 5 do 30 znaków", groups = RegistrationValidator.class)
    @NotBlank(groups = RegistrationValidator.class)
    private String password2;

    @Column(length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String firstName;

    @Column(length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String lastName;

    @Column(length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String city;

    @Column(length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String street;

    @Column(length = 50, name = "number_of_home", nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 50, message = "Maksymalnie 50 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String homeNumber;

    @Column(length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    @Email(message = "Wprowadź prawidłowy adres email")
    @Pattern(regexp = "^[a-zA-Z0-9]+[._-]*[a-zA-Z0-9]*@[a-zA-Z0-9]+([.][a-z]+)+([.][a-z]+)?$", message = "Wprowadź prawidłowy adres email", groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String email;

    @Column(name = "passed")
    private boolean passedEgzam;

    @Column(name = "last_test")
    private LocalDateTime lastTestTime;

    @Column(name = "last_slide", columnDefinition = "INT NOT NULL DEFAULT '1'")
    private int lastSlide;

    @Column(name = "admin")
    private boolean superUser;

    public void setPasswordHash(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPassedEgzam() {
        return passedEgzam;
    }

    public void setPassedEgzam(boolean passedEgzam) {
        this.passedEgzam = passedEgzam;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public LocalDateTime getLastTestTime() {
        return lastTestTime;
    }

    public void setLastTestTime(LocalDateTime lastTestTime) {
        this.lastTestTime = lastTestTime;
    }

    public int getLastSlide() {
        return lastSlide;
    }

    public void setLastSlide(int lastSlide) {
        this.lastSlide = lastSlide;
    }

    public boolean isSuperUser() {
        return superUser;
    }

    public void setSuperUser(boolean superUser) {
        this.superUser = superUser;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +

                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                ", email='" + email + '\'' +
                ", passedEgzam=" + passedEgzam +
                ", lastTestTime=" + lastTestTime +
                ", superUser=" + superUser +
                '}';
    }
}
