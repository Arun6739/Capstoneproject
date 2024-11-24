package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.BookHotelPage;
import pages.LoginPage;
import pages.SearchHotelPage;
import pages.SelectHotelPage;

public class HotelBookingTest {
    WebDriver driver;
    LoginPage loginPage;
    SearchHotelPage searchHotelPage;
    SelectHotelPage selectHotelPage;
    BookHotelPage bookHotelPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pcs-pc\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://adactinhotelapp.com/HotelAppBuild2/");

        loginPage = new LoginPage(driver);
        searchHotelPage = new SearchHotelPage(driver);
        selectHotelPage = new SelectHotelPage(driver);
        bookHotelPage = new BookHotelPage(driver);
    }

    @Test
    public void testHotelBookingWorkflow() {
        loginPage.login("Arunkumart315615", "Arun@2000");

        searchHotelPage.searchHotel("Sydney", "Hotel Creek", "Standard");

        selectHotelPage.selectHotel();

        selectHotelPage.clickContinue();

        bookHotelPage.fillBookingDetails(
            "Arun",                  
            "Kumar",                 
            "1234 Elm St, Sydney",   
            "4111111111111111",      
            "VISA",                 
            "October",              
            "2025",                  
            "123"                   
        );

        bookHotelPage.clickBookNow();
    }
}
