import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Testingng {

    WebDriver driver;
    @BeforeMethod
    public void OpenBrowser(){
        //1- Bridge
        String  key = "webdriver.chrome.driver";
        String value =System.getProperty("user.dir") + "\\Browsers\\chromedriver.exe";
        System.setProperty(key,value);

        //2- chrome object
        driver=new ChromeDriver();

        //3- Configration
        //3.1- maximize
        driver.manage().window().maximize();

        //3.2- set implicit wait
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //4- Navigate to website
        driver.get("https://magento.softwaretestingboard.com/");

    }

    @Test
    public void WriteValidReview(){
        //Test case number TC-Product -001

        //Preconditions
        //1- Sign in
        //1.1- click on sign in button
        driver.findElement(By.partialLinkText("Sign In")).click();

        //1.2- enter email "sw.sprints.team8@gmail.com"
        driver.findElement(By.cssSelector("input[name=\"login[username]\"]")).sendKeys("sw.sprints.team8@gmail.com");

        //1.3- enter the password "Team8@Password"
        driver.findElement(By.cssSelector("input[name=\"login[password]\"]")).sendKeys("Team8@Password");

        //1.4- click sign in button
        driver.findElement(By.cssSelector("button[type=\"submit\"][class=\"action login primary\"]")).click();


        //1- Choose the product you want to write a review for (e.g. https://magento.softwaretestingboard.com/breathe-easy-tank.html).
        //   Note that you must have purchased this product before.
        driver.get("https://magento.softwaretestingboard.com/breathe-easy-tank.html");

        //2- Click on add a review button.
        driver.findElement(By.cssSelector("a[class=\"action add\"]")).click();

        //3- Rate the product to 4.
//        driver.findElement((By.cssSelector("input[name=\"ratings[4]\"][id=\"Rating_5\"]"))).click();
        driver.findElement((By.xpath("//label[@id=\"Rating_5_label\"]"))).click();

        //4- The Nickname will be automatically the  same as the first name of the account.
//        driver.findElement(By.id("nickname_field")).sendKeys("Shamma"); //Not write as it is a default value

        //5- Write in the Summary "Good"
        driver.findElement(By.cssSelector("input[id=\"summary_field\"]")).sendKeys("Good");

        //6- Write in the review "Amazing"
        driver.findElement(By.cssSelector("textarea[id=\"review_field\"]")).sendKeys("Amazing");

        //7- Click on the submit button.
        driver.findElement(By.cssSelector("button[type=\"submit\"][class=\"action submit primary\"]")).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Compare actual results with expected results

        //1- a message is displayed at the top of the page "You submitted your review for moderation."
        String actualText= driver.findElement(By.cssSelector("div[data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]")).getText();
        String expectedText= "submitted your review for moderation.";
        Assert.assertTrue(actualText.contains(expectedText));

        //2- message color #006400
        String actualColor= driver.findElement(By.cssSelector("div[data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]")).getCssValue("color");
        actualColor= Color.fromString(actualColor).asHex();
        String expColor="#006400";
        Assert.assertEquals(actualColor,expColor);

    }

    @AfterMethod
    public void QuitDriver() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();



    }


}
