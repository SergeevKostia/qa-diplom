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

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
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
    @Order(1)
    @DisplayName("All fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveAllValidFieldRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

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
    @Order(2)
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
    @Order(3)
    @DisplayName("All fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveAllValidFieldENG() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

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

    @Test
    @Order(4)
    @DisplayName("All fields are filled with valid data(CreditPage, ENG)")
    void creditFormShouldHaveAllValidFieldENG() {
        CardPage start = new CardPage();
        start.cardPage();
        var CreditPage = start.buyInCredit();

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
    @Order(5)
    @DisplayName("All fields are filled with valid data and blocked card(PaymentPage, RUS)")
    void paymentFormShouldHaveAllValidFieldBlockedCardRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

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
    @Order(6)
    @DisplayName("All fields are filled with valid data and blocked card(CreditPage, RUS)")
    void creditFormShouldHaveAllValidFieldBlockedCardRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        var CreditPage = start.buyInCredit();

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
    @Order(7)
    @DisplayName("All fields are filled with valid data and blocked card(PaymentPage, ENG)")
    void paymentFormShouldHaveAllValidFieldBlockedCardENG() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

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

    @Test // БАГ
    @Order(8)
    @DisplayName("All fields are filled with valid data and blocked card(CreditPage, ENG)")
    void creditFormShouldHaveAllValidFieldBlockedCardENG() {
        CardPage start = new CardPage();
        start.cardPage();
        var CreditPage = start.buyInCredit();

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


    // Negative scenarios

    @Test // БАГ
    @Order(9)
    @DisplayName("All fields are empty(PaymentPage)")
    void paymentFormShouldHaveAllEmptyField() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(10)
    @DisplayName("All fields are empty(CreditPage)")
    void CreditFormShouldHaveAllEmptyField() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(11)
    @DisplayName("Number card empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyNumberCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();


        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(12)
    @DisplayName("Number card empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyNumberCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(13)
    @DisplayName("Month empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyMonthRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();


        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(14)
    @DisplayName("Month empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyMonthRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(15)
    @DisplayName("Year empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyYearRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(16)
    @DisplayName("Year empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyYearRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(17)
    @DisplayName("Holder empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyHolderRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(18)
    @DisplayName("Holder empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyHolderRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }


    @Test // БАГ
    @Order(19)
    @DisplayName("CVC empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(20)
    @DisplayName("CVC empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(21)
    @DisplayName("Non-existent card,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveNonExistentCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getInvalidFullCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationError();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(22)
    @DisplayName("Non-existent card,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveNonExistentCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getInvalidFullCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationError();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(23)
    @DisplayName("Non-existent short card,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveNonExistentShortCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getInvalidShortCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(24)
    @DisplayName("Non-existent Short card,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveNonExistentShortCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getInvalidShortCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(25)
    @DisplayName("Month above range,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveMonthAboveRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getInvalidMonthAboveYear());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(26)
    @DisplayName("Month above range,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveMonthAboveRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getInvalidMonthAboveYear());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(27)
    @DisplayName("Month below range,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveMonthBelowRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getInvalidMonthZero());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(28)
    @DisplayName("Month below range ,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveMonthBelowRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getInvalidMonthZero());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(29) // БАГ
    @DisplayName("Previous month,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHavePreviousMonthRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getInvalidPreviousMonth());
        paymentPage.setYear(DataHelper.getCurrentYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(30) // БАГ
    @DisplayName("Previous month,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHavePreviousMonthRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getInvalidPreviousMonth());
        creditPage.setYear(DataHelper.getCurrentYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(31)
    @DisplayName("Year above range,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveYearAboveRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getInvalidYearAboveRange());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(32)
    @DisplayName("Year above range,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveYearAboveRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getInvalidYearAboveRange());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(33)
    @DisplayName("Year below range,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveYearBelowRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getInvalidYearBelowRange());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(34)
    @DisplayName("Year below range,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveYearBelowRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getInvalidYearBelowRange());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(35)
    @DisplayName("Only name in holder,rest fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveOnlyNameHolderRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderOnlyNameRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(36)
    @DisplayName("Only name in holder,rest fields are filled with valid data(CreditPage, RUS)")
    void CreditFormShouldHaveOnlyNameHolderRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderOnlyNameRus());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(37)
    @DisplayName("Only name in holder,rest fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveOnlyNameHolderRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderOnlyNameENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(38)
    @DisplayName("Only name in holder,rest fields are filled with valid data(CreditPage, ENG)")
    void CreditFormShouldHaveOnlyNameHolderRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderOnlyNameENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(39)
    @DisplayName("Only surname in holder,rest fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveOnlySurnameHolderRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderOnlySurnameRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(40)
    @DisplayName("Only Surname in holder,rest fields are filled with valid data(CreditPage, RUS)")
    void CreditFormShouldHaveOnlySurnameHolderRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderOnlySurnameRus());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(41)
    @DisplayName("Only Surname in holder,rest fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveOnlySurnameHolderRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderOnlySurnameENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(42)
    @DisplayName("Only Surname in holder,rest fields are filled with valid data(CreditPage, ENG)")
    void CreditFormShouldHaveOnlySurnameHolderRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderOnlySurnameENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(43)
    @DisplayName("One letter in holder,rest fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveOneLetterHolderRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getOneLetterRUS());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(44)
    @DisplayName("One letter in holder,rest fields are filled with valid data(CreditPage, RUS)")
    void CreditFormShouldHaveOneLetterHolderRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getOneLetterRUS());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(45)
    @DisplayName("One letter in holder,rest fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveOneLetterHolderRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getOneLetterENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(46)
    @DisplayName("One letter in holder,rest fields are filled with valid data(CreditPage, ENG)")
    void CreditFormShouldHaveOneLetterHolderRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getOneLetterENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(47)
    @DisplayName("Holder with hyphenated,rest fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveHolderWithHyphenatedRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(48)
    @DisplayName("Holder with hyphenated,rest fields are filled with valid data(CreditPage, RUS)")
    void CreditFormShouldHaveHolderWithHyphenatedRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedRus());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(49)
    @DisplayName("Holder with hyphenated,rest fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveHolderWithHyphenatedRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(50)
    @DisplayName("Holder with hyphenated,rest fields are filled with valid data(CreditPage, ENG)")
    void CreditFormShouldHaveHolderWithHyphenatedRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }


    @Test // БАГ
    @Order(51)
    @DisplayName("Holder with hieroglyphs ,rest fields are filled with valid data(PaymentPage, CN)")
    void paymentFormShouldHaveHolderWithHieroglyphsRestFieldValidCN() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderCN());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(52)
    @DisplayName("Holder with hieroglyphs,rest fields are filled with valid data(CreditPage, CN)")
    void CreditFormShouldHaveHolderWithHieroglyphsRestFieldValidCN() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderCN());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(53)
    @DisplayName("Special symbol in holder,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveHolderWithSpecialSymbolRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getRandomSymbol());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(54)
    @DisplayName("Special symbol in holder,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveHolderWithSpecialSymbolRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getRandomSymbol());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(55)
    @DisplayName("Number in holder,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveHolderWithNumberRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getRandomNumber());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(56)
    @DisplayName("Number in holder,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveHolderWithNumberRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getRandomNumber());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(57)
    @DisplayName("Over flow holder,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveOverFlowHolderRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getHolderOverFlowString());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorOverFlow();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test // БАГ
    @Order(58)
    @DisplayName("Over Flow holder,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveOverFlowHolderRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getHolderOverFlowString());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorOverFlow();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(59)
    @DisplayName("One number in CVC,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveOneNumberInCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderRus());
        paymentPage.setCVC(DataHelper.getInvalidCVCCodeOneNumber());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(60)
    @DisplayName("One number in CVC,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveOneNumberInCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderRus());
        creditPage.setCVC(DataHelper.getInvalidCVCCodeOneNumber());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(61)
    @DisplayName("Two number in CVC,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveTwoNumberInCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderRus());
        paymentPage.setCVC(DataHelper.getInvalidCVCCodeTwoNumber());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(62)
    @DisplayName("Two number in CVC,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveTwoNumberInCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderRus());
        creditPage.setCVC(DataHelper.getInvalidCVCCodeTwoNumber());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

}

