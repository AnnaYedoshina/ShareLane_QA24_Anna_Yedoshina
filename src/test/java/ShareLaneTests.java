import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShareLaneTests {

    private static final String URL = "https://www.sharelane.com/cgi-bin/main.py";
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }

    @AfterClass
    public void tearDown() {
        driver.quit();

    }

    @BeforeMethod
    public void navigate() {
        driver.get(URL);

    }

    @Test
    public void positiveRegistrationTest() {
        WebElement signUpButton = driver.findElement(By.xpath("//a[@href='./register.py']"));
        signUpButton.click();
        WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
        continueButton.click();
        zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        Assert.assertFalse(zipCodeInput.isDisplayed());
        WebElement firstNameInput = driver.findElement(By.cssSelector("input[name=first_name]"));
        Assert.assertTrue(firstNameInput.isDisplayed());


    }

    @Test
    public void negativeRegistrationTest() {
        WebElement signUpButton = driver.findElement(By.xpath("//a[@href='./register.py']"));
        signUpButton.click();
        WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("1234");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
        continueButton.click();
        zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        Assert.assertTrue(zipCodeInput.isDisplayed());
        WebElement errorMessage = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(errorMessage.isDisplayed());
        String expectedErrorMessageText = "Oops, error on page. ZIP code should have 5 digits";
        Assert.assertEquals(errorMessage.getText(), expectedErrorMessageText);


    }

    @Test
    public void positiveSignUpTest() {
        WebElement signUpButton = driver.findElement(By.xpath("//a[@href='./register.py']"));
        signUpButton.click();
        WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
        continueButton.click();
        WebElement firstNameInput = driver.findElement(By.cssSelector("input[name=first_name]"));
        firstNameInput.clear();
        firstNameInput.sendKeys("Anna");
        WebElement lastNameInput = driver.findElement(By.cssSelector("input[name=last_name]"));
        lastNameInput.clear();
        lastNameInput.sendKeys("Yedoshina");
        WebElement emailInput = driver.findElement(By.cssSelector("input[name=email]"));
        emailInput.clear();
        emailInput.sendKeys("anna@gmail.com");
        WebElement passwordInput = driver.findElement(By.cssSelector("input[name=password1]"));
        passwordInput.clear();
        passwordInput.sendKeys("12345");
        WebElement confirmPasswordInput = driver.findElement(By.cssSelector("input[name=password2]"));
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys("12345");
        WebElement registerButton = driver.findElement(By.cssSelector("input[value=Register]"));
        registerButton.click();
        WebElement confirmationMessage = driver.findElement(By.cssSelector(".confirmation_message"));
        Assert.assertTrue(confirmationMessage.isDisplayed());
        String expectedConfirmationMessageText = "Account is created!";
        Assert.assertEquals(confirmationMessage.getText(), expectedConfirmationMessageText);


    }

    @Test
    public void negativeSignUpTest() {
        WebElement signUpButton = driver.findElement(By.xpath("//a[@href='./register.py']"));
        signUpButton.click();
        WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
        continueButton.click();
        WebElement firstNameInput = driver.findElement(By.cssSelector("input[name=first_name]"));
        firstNameInput.clear();
        WebElement lastNameInput = driver.findElement(By.cssSelector("input[name=last_name]"));
        lastNameInput.clear();
        lastNameInput.sendKeys("Yedoshina");
        WebElement emailInput = driver.findElement(By.cssSelector("input[name=email]"));
        emailInput.clear();
        emailInput.sendKeys("anna@gmail.com");
        WebElement passwordInput = driver.findElement(By.cssSelector("input[name=password1]"));
        passwordInput.clear();
        passwordInput.sendKeys("12345");
        WebElement confirmPasswordInput = driver.findElement(By.cssSelector("input[name=password2]"));
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys("12345");
        WebElement registerButton = driver.findElement(By.cssSelector("input[value=Register]"));
        registerButton.click();
        WebElement errorConfirmationMessage = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(errorConfirmationMessage.isDisplayed());
        String expectedErrorConfirmationMessageText = "Oops, error on page. Some of your fields have invalid data or email was previously used";
        Assert.assertEquals(errorConfirmationMessage.getText(), expectedErrorConfirmationMessageText);


    }
}
