package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    @FindBy(id = "login2")
    private WebElement loginLink;

    @FindBy(id = "logout")
    private WebElement logoutButton;

    @FindBy(css = ".close")
    private WebElement closeButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openLogin() {
        loginLink.click();
    }

    public void login(String username, String password) {
        driver.findElement(By.id("loginusername")).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
    }

    public boolean isUserLoggedIn() {
        try {
            return logoutButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void addProductToCart(String productName) {
        WebElement product = driver.findElement(By.xpath("//a[text()='" + productName + "']"));
        product.click();
        driver.findElement(By.xpath("//a[text()='Add to cart']")).click();
        handleAlertIfPresent();
    }

    public CartPage goToCart() {
        driver.findElement(By.id("cartur")).click();
        return new CartPage(driver);
    }

    public void closeModal() {
        try {
            scrollToElement(closeButton);
            clickUsingJavaScript(closeButton);
        } catch (NoSuchElementException e) {
            System.out.println("Close button not found.");
        }
    }

    private void handleAlertIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert text: " + alert.getText());
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("No alert present.");
        }
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void clickUsingJavaScript(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
