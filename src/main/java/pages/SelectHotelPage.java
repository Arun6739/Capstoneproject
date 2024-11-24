package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectHotelPage {
    WebDriver driver;

        private By selectHotelRadioButton = By.name("radiobutton_0");  
    private By continueButton = By.name("continue");

    public SelectHotelPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectHotel() {
        WebElement selectHotelButton = driver.findElement(selectHotelRadioButton);
        selectHotelButton.click();
    }

    public void clickContinue() {
        WebElement continueButtonElement = driver.findElement(continueButton);
        continueButtonElement.click();
    }
}
