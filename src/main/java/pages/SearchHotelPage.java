package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SearchHotelPage {
    WebDriver driver;

    private By locationDropdown = By.id("location");
    private By hotelDropdown = By.id("hotels");
    private By roomTypeDropdown = By.id("room_type");
    private By searchButton = By.id("Submit");

    public SearchHotelPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchHotel(String location, String hotel, String roomType) {
        Select locationSelect = new Select(driver.findElement(locationDropdown));
        locationSelect.selectByVisibleText(location);

        Select hotelSelect = new Select(driver.findElement(hotelDropdown));
        hotelSelect.selectByVisibleText(hotel);

        Select roomTypeSelect = new Select(driver.findElement(roomTypeDropdown));
        roomTypeSelect.selectByVisibleText(roomType);

        WebElement searchButtonElement = driver.findElement(searchButton);
        searchButtonElement.click();
    }
}
