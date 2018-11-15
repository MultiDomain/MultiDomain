import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class headlessBeowser {
    public static void main(String[] args) {

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/Mac/chromedriver");
        } else if (os.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/Windows/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/Linux/chromedriverlx");
        }

        HtmlUnitDriver unitDriver = new HtmlUnitDriver();
        unitDriver.get("http://google.com");

        System.out.println("Title of the page is -> " + unitDriver.getTitle());

        WebElement searchBox = unitDriver.findElement(By.name("q"));

        searchBox.sendKeys("Selenium");

        WebElement button = unitDriver.findElement(By.name("btnK"));

        button.click();

        System.out.println("Title of the page is -> " + unitDriver.getTitle());

    }

}
