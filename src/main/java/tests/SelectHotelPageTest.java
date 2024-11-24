package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage1;
import pages.SelectHotelPage1;
import pages.SearchHotelPage1;
import utils.ExcelUtils;

import java.io.IOException;
import java.util.List;

public class SelectHotelPageTest {
    WebDriver driver;
    LoginPage1 loginPage;
    SearchHotelPage1 searchHotelPage;
    SelectHotelPage1 selectHotelPage;
    String username;
    String password;

    @BeforeClass
    public void setup() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pcs-pc\\Downloads\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage1(driver);
        searchHotelPage = new SearchHotelPage1(driver);
        selectHotelPage = new SelectHotelPage1(driver);

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

        searchHotelPage.searchHotels("Melbourne", " ", "Standard", "3", "23/11/2024", "24/11/2024", "1", "0");
        Assert.assertTrue(searchHotelPage.isSearchSuccessful(), "Search failed!");
    }

    @Test(priority = 1)
    public void testSelectHotelOptions() {
        try {
            Assert.assertTrue(selectHotelPage.isOnSelectHotelPage(), "Not on Select Hotel Page!");

            List<WebElement> radioButtons = selectHotelPage.getRadioButtons();
            Assert.assertTrue(radioButtons.size() > 0, "No radio buttons available on the Select Hotel page!");

            for (int i = 0; i < radioButtons.size(); i++) {
                System.out.println("Testing radio button index: " + i);

                selectHotelPage.selectRadioButton(i);

                Thread.sleep(2000);

                selectHotelPage.clickContinue();
                Thread.sleep(2000); 
                Assert.assertTrue(selectHotelPage.isNextPageDisplayed(), "Failed to navigate to the next page for radio button index: " + i);

                selectHotelPage.clickCancel();
                Thread.sleep(2000); 
            }
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
