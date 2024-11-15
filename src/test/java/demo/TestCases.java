package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import org.testng.Assert;

public class TestCases {
    ChromeDriver driver;
    
     /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     /*https://forms.gle/wjPkzeSEk1CM7KgGA
      * Navigate to this google form : https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform
        Fill in Crio Learner in the 1st text box
        Write down "I want to be the best QA Engineer! 1710572021'' where 1710572021 is variable - needs to be the current epoch time.
        Enter your Automation Testing experience in the next radio button
        Select Java, Selenium and TestNG from the next check-box
        Provide how you would like to be addressed in the next dropdown
        Provided the current date minus 7 days in the next date field, it should be dynamically calculated and not hardcoded.
        Provide the time 07:30 in the next field (Can also be in 24 hour clock)
        Submit the form
        You will see a success message on the website. Print the same message on the console upon successful completion
        */
        @Test
        public void testCase01() throws InterruptedException{
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
            Thread.sleep(2000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            //wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputElement));

            WebElement nameInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[contains(@class,'zHQkBf')])[1]")));
            Wrappers.enterText(nameInputElement, "Crio Learner");
            System.out.println("Successfully entered name value");       
            Thread.sleep(2000);

            WebElement practicingAutomationElement = driver.findElement(By.xpath("//textarea[@aria-label='Your answer']"));
            String practicingAutomationText = "I want to be the best QA Engineer!"; 
            String epochTimeString = Wrappers.getEpochTimeAsString();
            Wrappers.enterText(practicingAutomationElement, practicingAutomationText+" "+epochTimeString);    
            System.out.println("Successfully entered practicingAutomationText value");       
            Thread.sleep(2000);

            Wrappers.radioButtonClick(driver, "3 - 5");
            System.out.println("Successfully selected radiio button");
            Thread.sleep(2000);

            Wrappers.checkboxSelection(driver, "Java");
            Wrappers.checkboxSelection(driver, "Selenium");
            Wrappers.checkboxSelection(driver, "TestNG");
            System.out.println("Successfully selected multiple checkboxes");
            Thread.sleep(2000);

            
            WebElement howYouShouldBeAddressedDropdownElement = driver.findElement(By.xpath("(//div[@role=\"presentation\"])[1]"));
            Wrappers.clickOnElement(howYouShouldBeAddressedDropdownElement, driver);
            Thread.sleep(2000);
            Wrappers.clickOnSalutationOption(driver, "Mr");
            System.out.println("Successfully entered salutation value");
            Thread.sleep(2000);

            //input[@type="date"]
            WebElement dateFieldElement = driver.findElement(By.xpath("//input[@type=\"date\"]"));
            String date = Wrappers.dateSevenDaysAgo();
            Wrappers.enterText(dateFieldElement, date);
            System.out.println("Successfully entered date 7 days ago value");
            Thread.sleep(2000);

            WebElement hourElement = driver.findElement(By.xpath("(//input[@role='combobox'])[1]"));
            WebElement minuteElement = driver.findElement(By.xpath("(//input[@role='combobox'])[2]"));
            Wrappers.enterText(hourElement, "07");
            System.out.println("Successfully entered hour value");
            Thread.sleep(2000);

            Wrappers.enterText(minuteElement, "30");
            System.out.println("Successfully entered minute value");
            Thread.sleep(2000);

            WebElement submitButtonElement = driver.findElement(By.xpath("//span[text()='Submit']"));
            Wrappers.clickOnElement(submitButtonElement, driver);
            System.out.println("Successfully clicked on Submit button");
            Thread.sleep(2000);

            //div[@class="vHW8K"]
            WebElement responseMessageElement = driver.findElement(By.xpath("//div[@class=\"vHW8K\"]"));
            String expectedResponse = "Thanks for your response, Automation Wizard!";
            Assert.assertEquals(responseMessageElement.getText().trim(), expectedResponse);
            System.out.println(responseMessageElement.getText());
            Thread.sleep(2000);

        }
        
       /*
        * Do not change the provided methods unless necessary, they will help in automation and assessment
        */
       @BeforeTest
       public void startBrowser()
       {
           System.setProperty("java.util.logging.config.file", "logging.properties");
   
           // NOT NEEDED FOR SELENIUM MANAGER
           // WebDriverManager.chromedriver().timeout(30).setup();
   
           ChromeOptions options = new ChromeOptions();
           LoggingPreferences logs = new LoggingPreferences();
   
           logs.enable(LogType.BROWSER, Level.ALL);
           logs.enable(LogType.DRIVER, Level.ALL);
           options.setCapability("goog:loggingPrefs", logs);
           options.addArguments("--remote-allow-origins=*");
   
           System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 
   
           driver = new ChromeDriver(options);
   
           driver.manage().window().maximize();
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       }
   

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}