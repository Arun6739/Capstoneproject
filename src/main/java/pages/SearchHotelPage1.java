package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchHotelPage1 {
    WebDriver driver;
    WebDriverWait wait;

    By locationDropdown = By.id("location");
    By hotelsDropdown = By.id("hotels");
    By roomTypeDropdown = By.id("room_type");
    By roomNosDropdown = By.id("room_nos");
    By checkInDateField = By.id("datepick_in");
    By checkOutDateField = By.id("datepick_out");
    By adultsPerRoomDropdown = By.id("adult_room");
    By childrenPerRoomDropdown = By.id("child_room");
    By searchButton = By.id("Submit");
    By resetButton = By.id("Reset");
    By resultsTable = By.id("hotel_form"); 
    By searchHotelLink =By.linkText("Search Hotel");
    By BookedItinerary=By.linkText("Booked Itinerary");
    By changepassword =By.linkText("Change Password");
    By logoutLink = By.linkText("Logout");
    
    
    By errorMessageLocator = By.className("reg_error"); 

    public SearchHotelPage1(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10); 
    }

    public void navigateToSearchHotelPage() {
        driver.navigate().to("https://adactinhotelapp.com/HotelAppBuild2/SearchHotel.php");
    }

    public void searchHotels(String location, String hotel, String roomType, String noOfRooms, String checkInDate, String checkOutDate, String adults, String children) {
        if (!location.isEmpty()) driver.findElement(locationDropdown).sendKeys(location);
        if (!hotel.isEmpty()) driver.findElement(hotelsDropdown).sendKeys(hotel);
        if (!roomType.isEmpty()) driver.findElement(roomTypeDropdown).sendKeys(roomType);
        if (!noOfRooms.isEmpty()) driver.findElement(roomNosDropdown).sendKeys(noOfRooms);
        if (!checkInDate.isEmpty()) driver.findElement(checkInDateField).clear();
        if (!checkInDate.isEmpty()) driver.findElement(checkInDateField).sendKeys(checkInDate);
        if (!checkOutDate.isEmpty()) driver.findElement(checkOutDateField).clear();
        if (!checkOutDate.isEmpty()) driver.findElement(checkOutDateField).sendKeys(checkOutDate);
        if (!adults.isEmpty()) driver.findElement(adultsPerRoomDropdown).sendKeys(adults);
        if (!children.isEmpty()) driver.findElement(childrenPerRoomDropdown).sendKeys(children);

        driver.findElement(searchButton).click();
    }

    public void resetForm() {
        driver.findElement(resetButton).click();
    }

    public boolean isSearchSuccessful() {
        try {
            wait.until(ExpectedConditions.urlContains("SelectHotel.php"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator)); 
            return true;
        } catch (Exception e) {
            return false; 
        }
    }

    public boolean areResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(resultsTable));
            return true; 
        } catch (Exception e) {
            return false; 
        }
    }
    
    public boolean isCheckOutErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_span"))); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLocationErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("location_span"))); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkLinksOnSearchHotelPage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10); 

            WebElement searchHotelLinkElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Search Hotel")));
            searchHotelLinkElement.click();
            if (!driver.getCurrentUrl().contains("SearchHotel.php")) return false;
            driver.navigate().back();

            WebElement bookedItineraryLinkElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Booked Itinerary")));
            bookedItineraryLinkElement.click();
            if (!driver.getCurrentUrl().contains("BookedItinerary.php")) return false;
            driver.navigate().back();

            WebElement changePasswordLinkElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Change Password")));
            changePasswordLinkElement.click();
            if (!driver.getCurrentUrl().contains("ChangePassword.php")) return false;
            driver.navigate().back();

            WebElement logoutLinkElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
            logoutLinkElement.click();
            if (!driver.getCurrentUrl().contains("Logout.php")) return false;

            driver.navigate().back(); 
            return true; 
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    }
}
