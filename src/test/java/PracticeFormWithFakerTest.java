import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class PracticeFormWithFakerTest {

    @Test
    public void fillFieldsPracticeForm() {
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName  = faker.name().lastName();
        String email     = faker.internet().emailAddress();
        String gender    = "Male";
        String phone     = faker.phoneNumber().subscriberNumber(10);
        String subject   = "English";
        String hobbies = "Sports";
        String address   = faker.address().fullAddress();
        String state     = "NCR";
        String city      = "Delhi";

        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").$(byValue("1994")).click();
        $(".react-datepicker__month-select").$(byValue("0")).click();
        $(".react-datepicker__month").$(byText("5")).click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $(byText(hobbies)).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/frog.jpg"));
        $("#currentAddress").setValue(address).scrollTo();
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(text(email));
        $(".table-responsive").shouldHave(text(gender));
        $(".table-responsive").shouldHave(text(phone));
        $(".table-responsive").shouldHave(text("05 January,1994"));
        $(".table-responsive").shouldHave(text(subject));
        $(".table-responsive").shouldHave(text(hobbies));
        $(".table-responsive").shouldHave(text("frog.jpg"));
        $(".table-responsive").shouldHave(text(address));
        $(".table-responsive").shouldHave(text(state + " " + city));
    }
}