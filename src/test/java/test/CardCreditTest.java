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
import page.CreditPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CardCreditTest {

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

    @Test
    @DisplayName("All fields are filled with valid data(CreditPage, RUS)")
    void creditFormShouldHaveAllValidFieldRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        var CreditPage = start.buyInCredit();

        CreditPage.setCardNumber(DataHelper.getValidWorkCard());
        CreditPage.setMonth(DataHelper.getValidMonth());
        CreditPage.setYear(DataHelper.getValidYear());
        CreditPage.setHolder(DataHelper.getValidHolderRus());
        CreditPage.setCVC(DataHelper.getValidCVCCode());
        CreditPage.buttonContinueClick();
        CreditPage.checkNotificationGood();
        String actual = SQLHelper.getCreditStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("All fields are filled with valid data(CreditPage, ENG)")
    void creditFormShouldHaveAllValidFieldENG() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage CreditPage = start.buyInCredit();

        CreditPage.setCardNumber(DataHelper.getValidWorkCard());
        CreditPage.setMonth(DataHelper.getValidMonth());
        CreditPage.setYear(DataHelper.getValidYear());
        CreditPage.setHolder(DataHelper.getValidHolderENG());
        CreditPage.setCVC(DataHelper.getValidCVCCode());
        CreditPage.buttonContinueClick();
        CreditPage.checkNotificationGood();
        String actual = SQLHelper.getCreditStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }

    @Test // БАГ
    @DisplayName("All fields are filled with valid data and blocked card(CreditPage, RUS)")
    void creditFormShouldHaveAllValidFieldBlockedCardRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage CreditPage = start.buyInCredit();

        CreditPage.setCardNumber(DataHelper.getValidBlockedCard());
        CreditPage.setMonth(DataHelper.getValidMonth());
        CreditPage.setYear(DataHelper.getValidYear());
        CreditPage.setHolder(DataHelper.getValidHolderRus());
        CreditPage.setCVC(DataHelper.getValidCVCCode());
        CreditPage.buttonContinueClick();
        CreditPage.checkNotificationError();
        String actual = SQLHelper.getCreditStatus();
        String expected = "DECLINED";
        assertEquals(expected, actual);
    }

    @Test // БАГ
    @DisplayName("All fields are filled with valid data and blocked card(CreditPage, ENG)")
    void creditFormShouldHaveAllValidFieldBlockedCardENG() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage CreditPage = start.buyInCredit();

        CreditPage.setCardNumber(DataHelper.getValidBlockedCard());
        CreditPage.setMonth(DataHelper.getValidMonth());
        CreditPage.setYear(DataHelper.getValidYear());
        CreditPage.setHolder(DataHelper.getValidHolderENG());
        CreditPage.setCVC(DataHelper.getValidCVCCode());
        CreditPage.buttonContinueClick();
        CreditPage.checkNotificationError();
        String actual = SQLHelper.getCreditStatus();
        String expected = "DECLINED";
        assertEquals(expected, actual);
    }

    @Test // БАГ
    @DisplayName("All fields are empty(CreditPage)")
    void CreditFormShouldHaveAllEmptyField() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Number card empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyNumberCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Month empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyMonthRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Year empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyYearRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Holder empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyHolderRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("CVC empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Non-existent card,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveNonExistentCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getInvalidFullCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationError();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Non-existent Short card,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveNonExistentShortCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getInvalidShortCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Month above range,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveMonthAboveRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getInvalidMonthAboveYear());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Month below range ,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveMonthBelowRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getInvalidMonthZero());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test// БАГ
    @DisplayName("Previous month,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHavePreviousMonthRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getInvalidPreviousMonth());
        creditPage.setYear(DataHelper.getCurrentYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Year above range,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveYearAboveRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getInvalidYearAboveRange());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Year below range,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveYearBelowRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getInvalidYearBelowRange());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Only name in holder,rest fields are filled with valid data(CreditPage, RUS)")
    void CreditFormShouldHaveOnlyNameHolderRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderOnlyNameRus());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Only name in holder,rest fields are filled with valid data(CreditPage, ENG)")
    void CreditFormShouldHaveOnlyNameHolderRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderOnlyNameENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Only Surname in holder,rest fields are filled with valid data(CreditPage, RUS)")
    void CreditFormShouldHaveOnlySurnameHolderRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderOnlySurnameRus());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Only Surname in holder,rest fields are filled with valid data(CreditPage, ENG)")
    void CreditFormShouldHaveOnlySurnameHolderRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderOnlySurnameENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("One letter in holder,rest fields are filled with valid data(CreditPage, RUS)")
    void CreditFormShouldHaveOneLetterHolderRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getOneLetterRUS());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("One letter in holder,rest fields are filled with valid data(CreditPage, ENG)")
    void CreditFormShouldHaveOneLetterHolderRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getOneLetterENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Holder with hyphenated,rest fields are filled with valid data(CreditPage, RUS)")
    void CreditFormShouldHaveHolderWithHyphenatedRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedRus());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Holder with hyphenated,rest fields are filled with valid data(CreditPage, ENG)")
    void CreditFormShouldHaveHolderWithHyphenatedRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Holder with hieroglyphs,rest fields are filled with valid data(CreditPage, CN)")
    void CreditFormShouldHaveHolderWithHieroglyphsRestFieldValidCN() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderCN());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Special symbol in holder,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveHolderWithSpecialSymbolRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getRandomSymbol());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Number in holder,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveHolderWithNumberRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getRandomNumber());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test // БАГ
    @DisplayName("Over Flow holder,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveOverFlowHolderRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getHolderOverFlowString());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorOverFlow();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("One number in CVC,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveOneNumberInCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderRus());
        creditPage.setCVC(DataHelper.getInvalidCVCCodeOneNumber());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Two number in CVC,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveTwoNumberInCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderRus());
        creditPage.setCVC(DataHelper.getInvalidCVCCodeTwoNumber());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }


}
