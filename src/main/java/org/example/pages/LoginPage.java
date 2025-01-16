package org.example.pages;

import org.openqa.selenium.*;
import java.util.Set;


public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        WebElement usernameField = waitForElement(By.id("loginusername"), 10);
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Log in']"));
        loginButton.click();
    }

    public boolean isUserLoggedIn() {
        try {
            WebElement logoutButton = driver.findElement(By.id("logout"));
            return logoutButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void saveCookies() {
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("Cookie: " + cookie.getName() + " = " + cookie.getValue());
        }
    }
}