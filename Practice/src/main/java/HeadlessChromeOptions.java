import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessChromeOptions {

    public static void main(String[] args) {

        WebDriver driver = null;


        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/Mac/chromedriver");
        } else if (os.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/Windows/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/Linux/chromedriver");
        }

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=400,1000");
        chromeOptions.addArguments("headless");

        driver = new ChromeDriver(chromeOptions);
        String url = "http://www.yahoo.com";
        try {
            driver.get(url);

            System.out.println(driver.getTitle());

        } catch (Exception e) {

        } finally {
            driver.manage().deleteAllCookies();
            driver.close();

            driver.quit();
        }
    }
}
