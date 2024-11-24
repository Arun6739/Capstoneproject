package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookHotelPage {

    WebDriver driver;
    WebDriverWait wait;

    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By billingAddressField = By.id("address");
    private By creditCardNoField = By.id("cc_num");
    private By creditCardTypeDropdown = By.id("cc_type");
    private By expiryDateField = By.id("cc_exp_month");
    private By expiryYearField = By.id("cc_exp_year");
    private By cvvField = By.id("cc_cvv");
    private By bookNowButton = By.id("book_now");

    public BookHotelPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    public void fillBookingDetails(String firstName, String lastName, String billingAddress,
                                   String ccNum, String ccType, String expiryMonth,
                                   String expiryYear, String cvv) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingAddressField)).sendKeys(billingAddress);
        wait.until(ExpectedConditions.visibilityOfElementLocated(creditCardNoField)).sendKeys(ccNum);

        WebElement creditCardType = wait.until(ExpectedConditions.elementToBeClickable(creditCardTypeDropdown));
        creditCardType.sendKeys(ccType);

        wait.until(ExpectedConditions.visibilityOfElementLocated(expiryDateField)).sendKeys(expiryMonth);
        wait.until(ExpectedConditions.visibilityOfElementLocated(expiryYearField)).sendKeys(expiryYear);

        wait.until(ExpectedConditions.visibilityOfElementLocated(cvvField)).sendKeys(cvv);
    }

    public void clickBookNow() {
        wait.until(ExpectedConditions.elementToBeClickable(bookNowButton)).click();
    }
}
