package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.BookHotelPage1;
import pages.LoginPage1;
import pages.SearchHotelPage1;
import pages.SelectHotelPage1;
import utils.ExcelUtils;

public class BookHotelPageTest {
    WebDriver driver;
    LoginPage1 loginPage;
    SearchHotelPage1 searchHotelPage;
    SelectHotelPage1 selectHotelPage;
    BookHotelPage1 bookHotelPage;
    WebDriverWait wait;

    @BeforeClass
    public void setup() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pcs-pc\\Downloads\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://adactinhotelapp.com/HotelAppBuild2/");

        loginPage = new LoginPage1(driver);
        searchHotelPage = new SearchHotelPage1(driver);
        selectHotelPage = new SelectHotelPage1(driver);
        bookHotelPage = new BookHotelPage1(driver);

        String[] credentials = ExcelUtils.getLoginCredentials("E:\\javaProgramming\\capstone\\src\\main\\java\\utils\\LoginData.xlsx", "Sheet1");
        loginPage.login(credentials[0], credentials[1]);
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed!");

        searchHotelPage.searchHotels("Sydney", "Hotel Creek", "Standard", "1", "24/12/2024", "25/12/2024", "1", "0");
        Assert.assertTrue(searchHotelPage.isSearchSuccessful(), "Hotel search failed!");

        selectHotelPage.selectHotelOption();
        selectHotelPage.clickContinue();
        Assert.assertTrue(selectHotelPage.isOnBookHotelPage(), "Failed to navigate to Book Hotel page!");
    }

    @DataProvider(name = "bookingData")
    public Object[][] bookingData() throws IOException {
        return ExcelUtils.getExcelData("E:\\javaProgramming\\capstone\\src\\main\\java\\utils\\LoginData.xlsx", "Sheet2");
    }

    @Test(dataProvider = "bookingData")
    public void testBookHotel(String firstName, String lastName, String billingAddress, String creditCardNo,
                              String creditCardType, String expiryMonth, String expiryYear, String cvv, String passFail) {
        if (firstName.isEmpty() && lastName.isEmpty() && billingAddress.isEmpty() && creditCardNo.isEmpty()) {
            System.out.println("Skipping test case with all empty fields.");
            return; 
        }

        bookHotelPage.fillBookingForm(firstName, lastName, billingAddress, creditCardNo, creditCardType, expiryMonth, expiryYear, cvv);
        bookHotelPage.clickBookNow();

        if ("Pass".equals(passFail)) {
            Assert.assertTrue(bookHotelPage.isBookingSuccessful(), "Booking was not successful!");
        } else {
            Assert.assertTrue(bookHotelPage.isErrorMessageDisplayed(), "Error message not displayed for invalid data!");
        }
    }
}
