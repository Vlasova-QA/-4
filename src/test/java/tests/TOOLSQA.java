package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static utils.RandomUtils.*;

public class TOOLSQA {

    @BeforeAll
    static void setup(){
        Configuration.startMaximized = true;
    }
    Faker faker = new Faker();
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = getRandomEmail(),
            mobile = getRandomPhone(),
            address = faker.address().fullAddress(),
            birthDay = String.valueOf(getRandomInt(1, 30)),
            birthYear = String.valueOf(getRandomInt(1980, 2010));

    @Test
    void TOOLSQARandome() {
        //Открытие страницы
        open("https://demoqa.com/automation-practice-form");
        $x("//div[@class='practice-form-wrapper']").shouldHave(text("Student Registration Form"));
        //Фамилия, Имя, email
        $x("//input[@id='firstName']").val(firstName);
        $x("//input[@id='lastName']").val(lastName);
        $x("//input[@id='userEmail']").val(email);
        //Выбор пола
        $x("//label[contains(text(), 'Male')]").click();
        //Номер телефона
        $x("//input[@id= 'userNumber']").val(mobile);
        // Дата рождения
        $x("//input[@id= 'dateOfBirthInput']").click();
        if (birthDay.length() == 1) birthDay = "0" + birthDay;
        $x("//select[@class='react-datepicker__month-select']").selectOption("October");
        $x("//select[@class= 'react-datepicker__year-select']").selectOption(birthYear);
        $x("//div[contains(@class, 'react-datepicker__day--0" + birthDay + "')]").click();
        //Заполнить поле Subjects
        $x("//input[@id= 'subjectsInput']").setValue("English").pressEnter();
        //Выбрать чек-бокс Hobbies
        $x("//label[contains(text(), 'Sports')]").click();
        //Выбрать файл
        $x("//input[@id='uploadPicture']").uploadFromClasspath("img/111.png");
        //Заполнить поле Сurrent Address
        $x("//*[@id= 'currentAddress']").val(address);
        //Выбрать чек-бокс State and City
        $x("//div[@id='state']").scrollTo();
        $x("//div[@id='state']").click();
        $x("//div[contains(@id,'react-select')][text()='Haryana']").click();
        $x("//div[@id='city']").click();
        $x("//div[contains(@id,'react-select')][text()='Karnal']").click();
        //Закрытие формы
        $x("//button[@id='submit']").click();
        $x("//div[@class='modal-title h4']").shouldHave(text("Thanks for submitting the form"));

        //Проверка регистрационной формы
        $x("//td[text()='Student Name']").parent().shouldHave(text(firstName + " " + lastName));
        $x("//td[text()='Student Email']").parent().shouldHave(text(email));
        $x("//td[text()='Gender']").parent().shouldHave(text("Male"));
        $x("//td[text()='Mobile']").parent().shouldHave(text(mobile));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(birthDay + " " + "October" + "," + birthYear));
        $x("//td[text()='Subjects']").parent().shouldHave(text("English"));
        $x("//td[text()='Hobbies']").parent().shouldHave(text("Sports"));
        $x("//td[text()='Picture']").parent().shouldHave(text("111.png"));
        $x("//td[text()='Address']").parent().shouldHave(text(address));
        $x("//td[text()='State and City']").parent().shouldHave(text("Haryana" + " " + "Karnal"));
    }
}