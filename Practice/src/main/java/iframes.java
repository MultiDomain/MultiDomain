import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class iframes {


    public static void main(String[] args) {
        WebDriver driver;
        String os = System.getProperty("os.name").toLowerCase();
        String url = "http://toolsqa.wpengine.com/iframe-practice-page/";

        if (os.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/chromedriver");
        } else if (os.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/chromedriverlx");
        }

        driver = new ChromeDriver();

        try {
            driver.get(url);
            JavascriptExecutor jsex = (JavascriptExecutor) driver;
            Integer frames = Integer.parseInt(jsex.executeScript("return window.length").toString());
            System.out.println("Number of frames in the page : " + frames);

            List framelist = driver.findElements(By.cssSelector("iframe"));
            System.out.println("Number of frames in page : " + framelist.size());
            driver.switchTo().frame(0);
            System.out.println(driver.getTitle());
            driver.switchTo().defaultContent();
            System.out.println(driver.getTitle());
            driver.switchTo().frame(1);
            System.out.println(driver.getTitle());
            driver.switchTo().defaultContent();
            driver.switchTo().frame("iframe1");

            driver.findElement(By.cssSelector("input[name ='firstname']")).sendKeys("Alexander");
            driver.switchTo().defaultContent();
            Thread.sleep(2000);
            driver.switchTo().frame("IF1");
            driver.findElement(By.cssSelector("input[name ='lastname']")).sendKeys("Hamilton");
            driver.switchTo().defaultContent();
            Thread.sleep(2000);

            WebElement e = driver.findElement(By.cssSelector("iframe#IF1"));

            driver.switchTo().frame(e);

            Select select = new Select(driver.findElement(By.cssSelector("select")));
            List<WebElement> continents = select.getOptions();

            System.out.println("Continents: "+(continents.size()));

            for (int i = 0; i<continents.size(); i++){
                System.out.print(continents.get(i).getText()+" | ");
            }

        } catch (Exception e) {
            System.out.println("Test Failed");
        } finally {
            driver.manage().deleteAllCookies();
            driver.close();
            driver.quit();
        }


    }

}
