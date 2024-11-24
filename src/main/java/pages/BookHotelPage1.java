package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookHotelPage1 {
    WebDriver driver;
    WebDriverWait wait;

    By firstNameField = By.id("first_name");
    By lastNameField = By.id("last_name");
    By billingAddressField = By.id("address");
    By creditCardNoField = By.id("cc_num");
    By creditCardTypeDropdown = By.id("cc_type");
    By expiryMonthDropdown = By.id("cc_exp_month");
    By expiryYearDropdown = By.id("cc_exp_year");
    By cvvField = By.id("cc_cvv");
    By bookNowButton = By.id("book_now");
    By errorMessageLocator = By.id("cc_num_span");
    By successMessageLocator = By.className("login_title");  

    public BookHotelPage1(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10); 
    }

    public void fillBookingForm(String firstName, String lastName, String billingAddress, String creditCardNo,
                                 String creditCardType, String expiryMonth, String expiryYear, String cvv) {
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(billingAddressField).clear();
        driver.findElement(billingAddressField).sendKeys(billingAddress);
        driver.findElement(creditCardNoField).clear();
        driver.findElement(creditCardNoField).sendKeys(creditCardNo);
        driver.findElement(creditCardTypeDropdown).sendKeys(creditCardType);
        driver.findElement(expiryMonthDropdown).sendKeys(expiryMonth);
        driver.findElement(expiryYearDropdown).sendKeys(expiryYear);
        driver.findElement(cvvField).clear();
        driver.findElement(cvvField).sendKeys(cvv);
    }

    public void clickBookNow() {
        driver.findElement(bookNowButton).click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false; 
        }
    }

    public boolean isBookingSuccessful() {
        try {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));
            return successMessage.isDisplayed();
           
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
