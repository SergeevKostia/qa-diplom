package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    // ПОЛЕ НОМЕР КАРТЫ
    public static cardNumber getValidWorkCard() {
        return new cardNumber("4444 4444 4444 4441");
    }

    public static cardNumber getValidBlockedCard() {
        return new cardNumber("4444 4444 4444 4442");
    }

    public static cardNumber getInvalidFullCard() {
        return new cardNumber("4444 4444 4444 4444");
    }

    public static cardNumber getInvalidShortCard() {
        return new cardNumber("4444 4444 4444 4");
    }



    // ПОЛЕ МЕСЯЦ
    public static String getValidMonth() {

        DateTimeFormatter to = DateTimeFormatter.ofPattern("MM");
        return LocalDate.now().format(to);
    }

    public static String getInvalidMonthAboveYear() {
        return String.valueOf((Math.random() * (86)) + 13);
    }

    public static String getInvalidMonthZero() {
        return "00";
    }

    public static String getInvalidPreviousMonth() {
        DateTimeFormatter to = DateTimeFormatter.ofPattern("MM");
        return LocalDate.now().minusMonths(1).format(to);
    }


    // ПОЛЕ ГОД
    public static String getValidYear() {
        DateTimeFormatter to = DateTimeFormatter.ofPattern("yy");
        int date = Integer.parseInt(LocalDate.now().format(to));
        return String.valueOf((Math.random() * (5)) + date);
    }

    public static String getCurrentYear(){
        DateTimeFormatter to = DateTimeFormatter.ofPattern("yy");
        return LocalDate.now().format(to);
    }

    public static String getInvalidYearAboveRange() {
        DateTimeFormatter to = DateTimeFormatter.ofPattern("yy");
        int date = 5 + Integer.parseInt(LocalDate.now().format(to));
        int rangeDate = 99 - date;
        return String.valueOf((Math.random() * (rangeDate)) + date);
    }

    public static String getInvalidYearBelowRange() {
        DateTimeFormatter to = DateTimeFormatter.ofPattern("yy");
        int date = Integer.parseInt(LocalDate.now().format(to)) -1;
        return String.valueOf((Math.random() * (date)) + 0);
    }


    //    ПОЛЕ ВЛАДЕЛЕЦ
    public static String getValidHolderRus() {
        Faker faker = new Faker(new Locale("ru"));

        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getValidHolderENG() {
        Faker faker = new Faker(new Locale("en"));

        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getInvalidHolderOnlyNameRus() {
        Faker faker = new Faker(new Locale("ru"));

        return faker.name().firstName();
    }

    public static String getInvalidHolderOnlyNameENG() {
        Faker faker = new Faker(new Locale("en"));

        return faker.name().firstName();
    }

    public static String getInvalidHolderOnlySurnameRus() {
        Faker faker = new Faker(new Locale("ru"));

        return faker.name().lastName();
    }

    public static String getInvalidHolderOnlySurnameENG() {
        Faker faker = new Faker(new Locale("en"));

        return faker.name().lastName();
    }

    public static String getOneLetterRUS() {
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        double randomIndex = Math.floor(Math.random() * alphabet.length());
        return String.valueOf(alphabet.charAt((int) randomIndex));
    }

    public static String getOneLetterENG() {
        Faker faker = new Faker(new Locale("en"));
        return faker.lorem().characters(1);
    }


    public static String getInvalidHolderWithHyphenatedRus() {
        Faker faker = new Faker(new Locale("ru"));

        return faker.name().firstName() + "-" + faker.name().lastName();
    }

    public static String getInvalidHolderWithHyphenatedENG() {
        Faker faker = new Faker(new Locale("en"));

        return faker.name().firstName() + "-" + faker.name().lastName();
    }

    public static String getInvalidHolderCN() {
        Faker faker = new Faker(new Locale("zh_CN"));

        return faker.name().firstName();
    }

    public static String getRandomSymbol() {
        String alphabet = "!@#$%^&*()~`№;:?[]{}/?|";
        double randomIndex = Math.floor(Math.random() * alphabet.length());
        return String.valueOf(alphabet.charAt((int) randomIndex));
    }

    public static String getRandomNumber() {
        return String.valueOf(Math.random() * (9999));
    }

    public static String getHolderOverFlowString() {
        Faker faker = new Faker();
        return faker.lorem().characters(21, 200);
    }


    //    ПОЛЕ CVC/CVV
    public static String getValidCVCCode() {
        return String.valueOf(Math.random() * (899) + 100);
    }

    public static String getInvalidCVCCodeOneNumber() {
        int number = (int) (Math.random() * (8) + 1);
        return String.valueOf(number);
    }

    public static String getInvalidCVCCodeTwoNumber() {
        int number = (int) (Math.random() * (89)) + 10;
        return String.valueOf(number);
    }


    @Value

    public static class cardNumber {
        String card;
    }
}
