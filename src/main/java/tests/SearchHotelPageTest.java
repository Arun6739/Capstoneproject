package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage1;
import pages.SearchHotelPage1;
import utils.ExcelUtils;

import java.io.IOException;

public class SearchHotelPageTest {
    WebDriver driver;
    SearchHotelPage1 searchHotelPage;
    LoginPage1 loginPage;
    String username;
    String password;
    WebDriverWait wait;  

    @BeforeClass
    public void setup() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pcs-pc\\Downloads\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage1(driver);
        searchHotelPage = new SearchHotelPage1(driver);

        wait = new WebDriverWait(driver, 10); 

        try {
            String[] credentials = ExcelUtils.getLoginCredentials("E:\\javaProgramming\\capstone\\src\\main\\java\\utils\\LoginData.xlsx", "Sheet1");
            username = credentials[0];
            password = credentials[1];
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Failed to read login credentials from Excel: " + e.getMessage());
        }

        driver.get("https://adactinhotelapp.com/index.php");

        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed!");
    }

    @Test(priority = 1)
    public void testSearchWithProperData() {
        try {
            searchHotelPage.searchHotels("Sydney", "Hotel Creek", "Standard", "1", "01/12/2024", "02/12/2024", "1", "0");
            Assert.assertTrue(searchHotelPage.isSearchSuccessful(), "Failed to proceed to the next page with proper data!");
        } catch (Exception e) {
            Assert.fail("Test failed due to: " + e.getMessage());
        } finally {
            searchHotelPage.navigateToSearchHotelPage();
        }
    }

    @Test(priority = 2)
    public void testSearchWithRequiredFieldsOnly() {
        try {
            searchHotelPage.searchHotels("Sydney", "", "", "", "01/12/2024", "02/12/2024", "", "");
            Assert.assertTrue(searchHotelPage.isSearchSuccessful(), "Failed to proceed to the next page with required fields only!");
        } catch (Exception e) {
            Assert.fail("Test failed due to: " + e.getMessage());
        } finally {
            searchHotelPage.navigateToSearchHotelPage();
        }
    }

    @Test(priority = 3)
    public void testSearchWithInterchangedDates() {
        try {
            searchHotelPage.searchHotels("Sydney", "Hotel Creek", "Standard", "1", "02/12/2024", "01/12/2024", "1", "0");
            Assert.assertTrue(searchHotelPage.isErrorMessageDisplayed(), "Error message not displayed for interchanged dates!");
        } catch (Exception e) {
            Assert.fail("Test failed due to: " + e.getMessage());
        } finally {
            searchHotelPage.navigateToSearchHotelPage();
        }
    }

    @Test(priority = 4)
    public void testSearchWithEmptyRequiredFields() {
        try {
            searchHotelPage.searchHotels("", "", "", "", "", "", "", "");
            Assert.assertTrue(searchHotelPage.isErrorMessageDisplayed(), "Error message not displayed for empty required fields!");
        } catch (Exception e) {
            Assert.fail("Test failed due to: " + e.getMessage());
        } finally {
            searchHotelPage.navigateToSearchHotelPage();
        }
    }

    @Test(priority = 5)
    public void testLinksOnSearchHotelPage() {
        try {
            WebElement searchHotelLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Search Hotel")));
            searchHotelLink.click();
            Assert.assertTrue(driver.getCurrentUrl().contains("SearchHotel.php"), "Failed to navigate to Search Hotel page");

            driver.navigate().refresh();

            WebElement bookedItineraryLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Booked Itinerary")));
            bookedItineraryLink.click();
            Assert.assertTrue(driver.getCurrentUrl().contains("BookedItinerary.php"), "Failed to navigate to Booked Itinerary page");

            driver.navigate().refresh();

            WebElement changePasswordLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Change Password")));
            changePasswordLink.click();
            Assert.assertTrue(driver.getCurrentUrl().contains("ChangePassword.php"), "Failed to navigate to Change Password page");

            driver.navigate().refresh();

        } catch (Exception e) {
            Assert.fail("Test failed due to: " + e.getMessage());
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
