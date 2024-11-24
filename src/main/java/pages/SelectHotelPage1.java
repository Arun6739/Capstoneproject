package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class SelectHotelPage1 {
    WebDriver driver;
    WebDriverWait wait;

    By radioButtonLocator = By.xpath("//input[@type='radio']"); 
    By radioButton0 = By.id("radiobutton_0");
    By continueButton = By.id("continue"); 
    By cancelButton = By.id("cancel"); 

    public SelectHotelPage1(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10); 
    }

    
    public List<WebElement> getRadioButtons() {
        return driver.findElements(radioButtonLocator);
    }

    public void selectRadioButton(int index) {
        List<WebElement> radioButtons = getRadioButtons();
        radioButtons.get(index).click();
    }

    public void selectHotelOption() {
        WebElement radioButton = driver.findElement(radioButton0);
        if (radioButton != null && !radioButton.isSelected()) {
            radioButton.click();
        }
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void clickCancel() {
        driver.findElement(cancelButton).click();
    }

    public boolean isNextPageDisplayed() {
        return driver.getCurrentUrl().contains("BookHotel.php");
    }

    public boolean isOnSelectHotelPage() {
        return driver.getCurrentUrl().contains("SelectHotel.php");
    }
    
    public boolean isOnBookHotelPage() {
        return driver.getCurrentUrl().contains("BookHotel.php");
    } 
    
    
}
