package hw_2023_04_24__2_10.service;

import hw_2023_04_24__2_10.exception.IncorrectNameException;
import hw_2023_04_24__2_10.exception.IncorrectSurnameException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static org.apache.commons.lang3.StringUtils.*;
@Service
public class ValidatorService {

    public String validateName(String name) {
        if (!isAlpha(name)) {
            throw new IncorrectNameException();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }

    public String validateSurname(String surname) {
        String[] surnames = surname.split("-"); // разделяем двойные фамилии "Мамин-Сибиряк"
        for (int i = 0; i < surnames.length; i++) {
            if (!isAlpha(surnames[i])) {
                throw new IncorrectSurnameException();
            }
            surnames[i] = StringUtils.capitalize(surnames[i].toLowerCase());
        }
        return String.join("-", surnames);// объединяем двойные фамилии
    }
}
