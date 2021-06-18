
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Test1 {

    private static final String SEARCH = "#hub-search > button";
    private static final String SEARCHBOX = "#hub-search-results > div > div > div > div > div > div.SearchBox1abqOlfT42cO > input";
    private static final String SEARCHRESULT = "#hub-search-results > div > div > div > div > div > div.SearchResults2YeeVI7JLCzu > div.SearchResults-list > a:nth-child(2)";
    private static final String JSON = "//*[@id=\"page-responses-getstatus-extended\"]/div[2]/div[1]/div/div[2]/div[1]/button[2]";
    private static final String PROCESSOR = "//*[@id=\"page-responses-getstatus-extended\"]/div[2]/div[1]/div/div[2]/div[2]/pre[2]/code/div/span[26]";
    private static final String STATUS = "//*[@id=\"page-responses-getstatus-extended\"]/div[2]/div[1]/div/div[2]/div[2]/pre[2]/code/div/span[35]";

    @Test //проверка URL
    public void Test1_1 () {
        System.setProperty("webdriver.chrome.driver", "F:\\dev\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            driver.get("https://api.encoding.com");
            WebElement elementSearch = wait.until(presenceOfElementLocated(By.cssSelector(SEARCH)));
            elementSearch.click();

            driver.findElement(By.cssSelector(SEARCHBOX)).sendKeys("getStatus" + Keys.ENTER);
            WebElement elementSearchBox = wait.until(presenceOfElementLocated(By.cssSelector(SEARCHRESULT)));
            elementSearchBox.click();

            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://api.encoding.com/reference#responses-getstatus-extended";
            Assert.assertEquals(actualUrl, expectedUrl);
        } catch (Exception e) {
            System.out.println("Test1_1 - failed");
        } finally {
            System.out.println("Test1_1 - finished");
            driver.quit();
        }
    }
    @Test //проверка значений
    public void Test1_2 () {
        System.setProperty("webdriver.chrome.driver", "F:\\dev\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            driver.get("https://api.encoding.com");
            WebElement elementSearch = wait.until(presenceOfElementLocated(By.cssSelector(SEARCH)));
            elementSearch.click();

            driver.findElement(By.cssSelector(SEARCHBOX)).sendKeys("getStatus" + Keys.ENTER);
            WebElement elementSearchBox = wait.until(presenceOfElementLocated(By.cssSelector(SEARCHRESULT)));
            elementSearchBox.click();

            WebElement elementJson = wait.until(presenceOfElementLocated(By.xpath(JSON)));
            elementJson.click();

            WebElement resultProcessor = wait.until(presenceOfElementLocated(By.xpath(PROCESSOR)));
            String expectedResultProcessor  = "\"[AMAZON | RACKSPACE]\"";
            String actualResultProcessor  = resultProcessor .getText();
            Assert.assertEquals(actualResultProcessor , expectedResultProcessor);

            WebElement resultStatus = wait.until(presenceOfElementLocated(By.xpath(STATUS)));
            String expectedResultStatus = "\"[Status]\"";
            String actualResultStatus = resultStatus.getText();
            Assert.assertEquals(actualResultStatus, expectedResultStatus);
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Test1_2 - failed");
        } finally {
            System.out.println("Test1_2 - finished");
            driver.quit();
        }
    }
}