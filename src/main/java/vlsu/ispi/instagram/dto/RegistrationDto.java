package vlsu.ispi.instagram.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RegistrationDto {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String middleName;
    private LocalDate birthdate;
}
