package stepDefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.qa.managers.PageObjectManager;
import com.qa.util.Xls_Reader;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.text.DecimalFormat;

public class CurrencyCalculatorPageSteps extends PageObjectManager {
    Xls_Reader testdata = new Xls_Reader("./src/test/resources/testdata/testData.xlsx");
    String localizationTestData = "localization";

    @Before
    public void beforeScenario() {
        launchBrowser();
    }

    @Given("User go to online currency exchange calculator")
    public void user_go_to_online_currency_exchange_calculator() throws Throwable {
        currencyCalcPage.gotoApplication();
        currencyCalcPage.goToCurrencyCalc();
        Thread.sleep(10000);
    }

    @When("User input {string} as sell input field")
    public void user_input_as_sell_input_field(String arg0) {
        currencyCalcPage.inputSellFieldValue(arg0);
    }

    @Then("Verify sell input field value showing {string}")
    public void verify_sell_input_field_value_showing(String arg0) {
        String sellInputField = currencyCalcPage.getSellInputFieldValue();
        System.out.println("Verify sell input value = " + sellInputField);
        Assert.assertEquals(arg0, sellInputField);
    }

    @Then("User input {string} as buy input field")
    public void user_input_as_buy_input_field(String arg0) {
        currencyCalcPage.inputBuyFieldValue(arg0);
    }

    @Then("Verify for inputting buy value sell input field emptied")
    public void verify_for_inputting_buy_value_sell_input_field_emptied() {
        String sellInputField = currencyCalcPage.getSellInputFieldValue();
        Assert.assertTrue(sellInputField.isEmpty());
    }


    @And("Verify buy input field value showing {string}")
    public void verifyBuyInputFieldValueShowing(String arg0) {
        String buyInputField = currencyCalcPage.getBuyInputFieldValue();
        Assert.assertEquals(arg0, buyInputField);
    }

    @Then("Verify for inputting sell value buy input field emptied")
    public void verifyForInputtingSellValueBuyInputFieldEmptied() {
        String buyInputField = currencyCalcPage.getBuyInputFieldValue();
        Assert.assertTrue(buyInputField.isEmpty());
    }

    @When("User change country")
    public void userChangeCountry() {
        String countryNo = testdata.getCellData(localizationTestData, "CountryNo", 2);
        System.out.println("country: " + countryNo);
        elementUtil.scrollToBottom();
        currencyCalcPage.clickFlagIcon();
        currencyCalcPage.clickToOpenCountryDropDown();
        currencyCalcPage.selectCountryFromDropDown(countryNo);
    }

    @And("Verify respective default currency set for selected country")
    public void verifyRespectiveDefaultCurrencySetForSelectedCountry() throws Throwable {
        String ex_defaultCurrency = testdata.getCellData(localizationTestData, "DefaultCurrency", 2);
        System.out.println("DefaultCurrency: " + ex_defaultCurrency);
        ExtentCucumberAdapter.addTestStepLog("Expected Default Currency by country: " + ex_defaultCurrency);
        currencyCalcPage.goToCurrencyCalc();
        Thread.sleep(10000);
        String ac_defaultCurrency = currencyCalcPage.getDefaultCurrencyName();
        ExtentCucumberAdapter.addTestStepLog("Actual Default Currency by country: " + ac_defaultCurrency);
        Assert.assertEquals(ex_defaultCurrency, ac_defaultCurrency);
    }

    @When("Verify loss amount showing for {int}{} currency")
    public void verifyLossAmountShowingForStCurrency(int arg0, String arg1) throws Throwable {
        String Y = currencyCalcPage.getPsAmount(arg0);
        String X = currencyCalcPage.getSwedBankAmount(arg0);

        float YF = Float.parseFloat(Y);
        float XF = Float.parseFloat(X);

        if (YF > XF) {
            DecimalFormat df = new DecimalFormat();
            df.setMinimumFractionDigits(2);
            df.setMaximumFractionDigits(2);
            float ex_loss_amn = XF - YF;
            String ex_loss_amn_string = df.format(ex_loss_amn);
            ExtentCucumberAdapter.addTestStepLog("Expected loss amount for " + arg0 + arg1 + " Currency: (" + ex_loss_amn_string + ")");

            if (currencyCalcPage.isLossAmnShowing(arg0)) {
                String ac_loss_amn = currencyCalcPage.getSwedBankLossAmount(arg0);
                ExtentCucumberAdapter.addTestStepLog("Actual loss amount for " + arg0 + arg1 + " Currency: " + ac_loss_amn);
                Assert.assertEquals("(" + ex_loss_amn_string + ")", ac_loss_amn);
            } else {
                ExtentCucumberAdapter.addTestStepLog("PS amount is higher than SwedBank amount, still loss amount is not showing");
                Assert.fail("PS amount is higher than SwedBank amount, still loss amount is not showing");
            }
        } else {
            ExtentCucumberAdapter.addTestStepLog("PS amount is lower than SwedBank amount, no loss amount");
            Assert.fail("PS amount is lower than SwedBank amount, no loss amount");
        }
    }
}
