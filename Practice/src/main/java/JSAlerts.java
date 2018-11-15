import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JSAlerts {

    public static void main(String[] args) {

        WebDriver driver = null;
        WebElement element = null;
        String url = null;
        String os = System.getProperty("os.name").toLowerCase();


        if (os.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/chromedriver");
        } else if (os.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/chromedriverlx");
        }

        try {

            driver = new ChromeDriver();
            url = "http://toolsqa.com/handling-alerts-using-selenium-webdriver/";
            driver.get(url);
            WebElement e = null;
            Alert alert = null;

            WebDriverWait exWait = new WebDriverWait(driver, 20);

//            e = exWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("button[onclick='pushAlert()']"))));
//            e.click();
//            exWait.until(ExpectedConditions.alertIsPresent());
//            alert = driver.switchTo().alert();
//            System.out.println(alert.getText());
//            Thread.sleep(2000);
//            alert.accept();

            driver.navigate().refresh();

            e = exWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("button[onclick='pushConfirm()']"))));
            e.click();
            exWait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            System.out.println(alert.getText());
            Thread.sleep(2000);
            alert.dismiss();

            driver.navigate().refresh();

            e = exWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("button[onclick='promptConfirm()']"))));
            e.click();
            exWait.until(ExpectedConditions.alertIsPresent());
            System.out.println("alert switched");
            alert = driver.switchTo().alert();
            alert.sendKeys("This is a prompt popup alert");

            System.out.println(alert.getText());
            alert.accept();
            Thread.sleep(2000);


        } catch (Exception ex) {
            System.out.println("Test Failed !!!");
            System.err.println(ex);
        } finally {
            driver.manage().deleteAllCookies();
            driver.close();
            driver.quit();
        }

    }
}
