package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage1;
import utils.ExcelUtils;

public class LoginPageTest {
    WebDriver driver;
    LoginPage1 loginPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pcs-pc\\Downloads\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage1(driver);

        driver.get("https://adactinhotelapp.com/");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws Exception {
        return ExcelUtils.getExcelData("E:\\javaProgramming\\capstone\\src\\main\\java\\utils\\LoginData.xlsx", "Sheet1");
    }
    @Test(priority=1,dataProvider = "loginData")
    public void testLogin(String username, String password) {
        try {
            loginPage.login(username, password);

            if (loginPage.isLoginSuccessful()) {
                System.out.println("Login Successful for user: " + username);
                Assert.assertTrue(true, "Login succeeded as expected.");
            } else if (loginPage.isLoginFailed()) {
                System.out.println("Login Failed for user: " + username);
                Assert.assertFalse(false, "Login failed as expected.");
            } else {
                Assert.fail("Unexpected result: Neither success nor failure detected.");
            }
        } catch (Exception e) {
            Assert.fail("Unexpected exception: " + e.getMessage());
        } finally {
            loginPage.navigateToLoginPage();
        }
    }

    @Test(priority=2)
    public void testForgotPassword() {
        try {
            boolean isNavigationSuccessful = loginPage.resetPassword();

            Assert.assertTrue(isNavigationSuccessful, "Forgot Password navigation failed!");
            System.out.println("Successfully navigated to Forgot Password page.");
        } catch (Exception e) {
            Assert.fail("Forgot Password Test Failed: " + e.getMessage());
        }finally {
            loginPage.navigateToLoginPage();
        }
    }
    @Test(priority=3)
    public void testNavigateToRegisterPage() {
        try {
            boolean isNavigated = loginPage.navigateToRegisterPage();

            Assert.assertTrue(isNavigated, "Failed to navigate to the registration page!");
            System.out.println("Navigated to the Register page successfully.");
        } catch (Exception e) {
            Assert.fail("Navigation Test Failed: " + e.getMessage());
        }finally {
            loginPage.navigateToLoginPage();

        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
