import org.example.pages.HomePage;
import org.example.pages.CartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Scanner;

public class UITests {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://www.demoblaze.com");
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAddToCartWithAlert() {
        homePage.openLogin();

        System.out.println("Введите логин и пароль вручную, затем нажмите кнопку 'Log in'.");
        pauseForManualInput();

        Assert.assertTrue(homePage.isUserLoggedIn(), "User is not logged in!");

        homePage.addProductToCart("Samsung galaxy s6");

        homePage.closeModal();

        CartPage cartPage = homePage.goToCart();
        Assert.assertTrue(cartPage.isProductInCart("Samsung galaxy s6"), "Product not found in cart!");
    }

    private void pauseForManualInput() {
        System.out.println("Нажмите Enter после завершения ввода логина и пароля.");
        new Scanner(System.in).nextLine();
    }
}
