package test;

import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.*;
import page.CardPage;

import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CardTest {



    @BeforeEach
    void setup() {
        open(System.getProperty("SUT.url", "http://localhost:8080/"));
       SQLHelper.clearDB();

    }

    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    // Positive scenarios

    @Test
    @DisplayName("All fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveAllValidFieldRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationGood();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("All fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveAllValidFieldENG() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationGood();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }


    @Test // БАГ
    @DisplayName("All fields are filled with valid data and blocked card(PaymentPage, RUS)")
    void paymentFormShouldHaveAllValidFieldBlockedCardRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidBlockedCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationError();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "DECLINED";
        assertEquals(expected, actual);
    }


    @Test // БАГ
    @DisplayName("All fields are filled with valid data and blocked card(PaymentPage, ENG)")
    void paymentFormShouldHaveAllValidFieldBlockedCardENG() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidBlockedCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationError();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "DECLINED";
        assertEquals(expected, actual);
    }


    // Negative scenarios

    @Test // БАГ
    @DisplayName("All fields are empty(PaymentPage)")
    void paymentFormShouldHaveAllEmptyField() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Number card empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyNumberCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();


        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Month empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyMonthRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();


        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Year empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyYearRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Holder empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyHolderRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("CVC empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Non-existent card,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveNonExistentCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getInvalidFullCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationError();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Non-existent short card,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveNonExistentShortCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getInvalidShortCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Month above range,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveMonthAboveRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getInvalidMonthAboveYear());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Month below range,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveMonthBelowRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getInvalidMonthZero());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test// БАГ
    @DisplayName("Previous month,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHavePreviousMonthRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getInvalidPreviousMonth());
        paymentPage.setYear(DataHelper.getCurrentYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Year above range,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveYearAboveRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getInvalidYearAboveRange());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Year below range,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveYearBelowRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getInvalidYearBelowRange());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Only name in holder,rest fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveOnlyNameHolderRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderOnlyNameRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Only name in holder,rest fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveOnlyNameHolderRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderOnlyNameENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Only surname in holder,rest fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveOnlySurnameHolderRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderOnlySurnameRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Only Surname in holder,rest fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveOnlySurnameHolderRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderOnlySurnameENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("One letter in holder,rest fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveOneLetterHolderRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getOneLetterRUS());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("One letter in holder,rest fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveOneLetterHolderRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getOneLetterENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Holder with hyphenated,rest fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveHolderWithHyphenatedRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Holder with hyphenated,rest fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveHolderWithHyphenatedRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Holder with hieroglyphs ,rest fields are filled with valid data(PaymentPage, CN)")
    void paymentFormShouldHaveHolderWithHieroglyphsRestFieldValidCN() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderCN());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Special symbol in holder,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveHolderWithSpecialSymbolRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getRandomSymbol());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Number in holder,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveHolderWithNumberRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getRandomNumber());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Over flow holder,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveOverFlowHolderRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getHolderOverFlowString());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorOverFlow();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("One number in CVC,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveOneNumberInCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderRus());
        paymentPage.setCVC(DataHelper.getInvalidCVCCodeOneNumber());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Two number in CVC,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveTwoNumberInCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderRus());
        paymentPage.setCVC(DataHelper.getInvalidCVCCodeTwoNumber());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

}

