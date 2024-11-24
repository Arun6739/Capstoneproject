package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage1 {
    WebDriver driver;
    WebDriverWait wait;

    By usernameField = By.id("username");
    By passwordField = By.id("password");
    By loginButton = By.id("login");
    By dashboard = By.id("username_show");
    By loginErrorMessage = By.className("auth_error");
    By forgotPasswordLink = By.cssSelector(".login_forgot a");
    By newUserLink = By.cssSelector(".login_register a");
    By emailField = By.id("emailadd_recovery");
    By resetButton = By.id("Submit");

    public LoginPage1(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    public void clickForgotPassword() {
        WebElement forgotPasswordLinkElement = wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink));
        forgotPasswordLinkElement.click();
    }

    public void login(String username, String password) {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElement.clear();
        passwordElement.sendKeys(password);

        WebElement loginButtonElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButtonElement.click();
    }

    public void navigateToLoginPage() {
        driver.navigate().to("https://adactinhotelapp.com/index.php");
    }

    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboard));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginFailed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginErrorMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean resetPassword() {
        try {
            clickForgotPassword();

            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.urlContains("ForgotPassword.php"));
            return true;
        } catch (Exception e) {
            return false; 
        }
    }

    public boolean navigateToRegisterPage() {
        try {
            WebElement registerLinkElement = wait.until(ExpectedConditions.elementToBeClickable(newUserLink));
            registerLinkElement.click();

            wait.until(ExpectedConditions.urlContains("Register.php")); 
            return true; 
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    }

}
