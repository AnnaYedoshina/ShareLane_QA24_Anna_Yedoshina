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
    public void navigate(){
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
}
