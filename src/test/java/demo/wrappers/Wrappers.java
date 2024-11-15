package demo.wrappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.text.DateFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public static void enterText(WebElement element, String text){
        try {
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getEpochTimeAsString(){
        long epoch = System.currentTimeMillis()/1000;
        String epochString = String.valueOf(epoch);
        return epochString;
    }

    public static void radioButtonClick(ChromeDriver driver, String expInYear){
        try {
            WebElement radioButtonElement = driver.findElement(By.xpath("//div[@aria-label='" + expInYear + "']"));
            // JavascriptExecutor js= (JavascriptExecutor) driver;
            // js.executeScript("arguments[0].click()", radioButtonElement);
            radioButtonElement.click();          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkboxSelection(ChromeDriver driver, String data){
        try {
            WebElement radioButtonElement = driver.findElement(By.xpath("//div[@data-answer-value='" + data + "']"));
            // JavascriptExecutor js= (JavascriptExecutor) driver;
            // js.executeScript("arguments[0].click()", radioButtonElement);
            radioButtonElement.click();          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickOnElement(WebElement element, ChromeDriver driver){
        try {
            JavascriptExecutor js= (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click()", element);
            // element.click();          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickOnSalutationOption(ChromeDriver driver, String data){
        try {
            WebElement howYouShouldBeAddressedOptions = driver.findElement(By.xpath("//div[@data-value='"+ data +"' and @role='option']"));
            howYouShouldBeAddressedOptions.click();          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String dateSevenDaysAgo(){
        LocalDate currentDate = LocalDate.now();
        LocalDate date7DaysAgo = currentDate.minusDays(7);
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String formatedDate = date7DaysAgo.format(formater);
        return formatedDate;

    }

}
