package steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static utils.BrowserSetup.*;
import static utils.SpreadSheetReader.singleColumnSpreadsheetReader;
import static utils.SpreadSheetWriter.setCellValuesStringList;
import static utils.SpreadSheetWriter.workbook;

public class CowinStepDefs extends BaseStepDef {

    private static final String testDataSpreadSheet = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testDatafiles" + File.separator + "testData.xls";
    private static final String resultsSpreadSheet = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testDatafiles" + File.separator + "Results.xls";
    public static String baseURL = "https://www.cowin.gov.in/home";
    WebDriver selectedDriver;


    public CowinStepDefs(HashMap<String, List<String>> var) {
        super(var);
    }

    @Given("^I select the browser \"([^\"]*)\"$")
    public void iSelectTheBrowser(String browser) {
        switch (browser) {
            case "Chrome":
                selectedDriver = chromeDriver();
                break;
            case "Firefox":
                selectedDriver = firefoxDriver();
                break;
            case "Edge":
                selectedDriver = edgeDriver();
                break;
            default:
                System.out.println("Wrong Input");
        }
    }

    @And("^I open the Cowin website$")
    public void iOpenTheCowinWebsite() {
        selectedDriver.get(baseURL);
    }

    @When("^I pick the zipcode from the spreadsheet and store it as \"([^\"]*)\"$")
    public void iPickTheZipcodeFromTheSpreadsheetAndStoreItAs(String zipcode) throws Throwable {
        List<String> zipCodes = singleColumnSpreadsheetReader(testDataSpreadSheet, "testData");
        vars.put(zipcode, zipCodes);
    }

    @Then("^I search the stored \"([^\"]*)\" zipcodes in the website and store it in the same spreadsheet$")
    public void iSearchTheStoredZipcodesInTheWebsiteAndStoreItInTheSameSpreadsheet(String zipCodes) {
        WebDriverWait wait = new WebDriverWait(selectedDriver, 30);
        List<String> getZipCodes = vars.get(zipCodes);
        for (String k : getZipCodes) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='mat-input-0']")));
            selectedDriver.findElement(By.xpath("//input[@id='mat-input-0']")).sendKeys(k);
            selectedDriver.findElement(By.className("pin-search-btn")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='row-disp']")));
            List<WebElement> getElements = selectedDriver.findElements(By.xpath("//*[@class='row-disp']"));
            List<String[]> getValues = new ArrayList<>();
            getElements.stream().forEach(l -> {
                getValues.add(new String[]{k, l.findElement(By.className("center-name-title")).getText(), l.findElement(By.className("center-name-text")).getText()});
            });
            try {
                setCellValuesStringList(resultsSpreadSheet, getValues);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @After("@cowin")
    public void AfterHook() throws IOException {
        workbook.close();
        selectedDriver.quit();
    }
}