import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Facebook {

    private WebElement element;

    public static void main(String[] args) {

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/chromedriver");
        } else if (os.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/chromedriverlx");
        }


        WebDriver driver = new ChromeDriver();

        String url = "https://www.facebook.com/";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
//        System.out.println(driver.getPageSource());

        try {
            driver.findElement(By.xpath("//*[@id=\'email\']")).click();
            driver.findElement(By.xpath("//input[@id=\'email\']")).sendKeys("Hello", Keys.ENTER);
            driver.navigate().to("http://www.yahoo.com");
            driver.navigate().back();
            driver.navigate().forward();
            driver.navigate().refresh();

            driver.get(url);
            driver.findElement(By.cssSelector("input[type='email']")).clear();
            driver.findElement(By.cssSelector("input[type='email']")).sendKeys("hello ");
            driver.findElement(By.cssSelector("input[type='password'][class ='inputtext']")).sendKeys("world", Keys.ENTER);

            WebElement element = null;

            driver.get("http://www.google.com");
            element = driver.findElement(By.cssSelector("input[title ='Search']"));
            element.click();
            element.sendKeys("CSS selector selenium tutorial", Keys.ENTER);

            Thread.sleep(5000);

            driver.get(url);

            element = driver.findElement(By.cssSelector("input[type='text'][name='reg_email_confirmation__']"));

            if (element.isDisplayed()) {
                System.out.println("Element is displayed");
            } else {
                System.out.println("Element is not displayed");
            }

            if (element.isEnabled()) {
                System.out.println("Element is enabled");
            } else {
                System.out.println("Element is not enabled");
            }

            List<WebElement> list = new ArrayList<>();
            list = driver.findElements(By.cssSelector("input[type='radio']"));

            for (WebElement e : list) {
                if (e.isSelected()) {
                    System.out.println(e.getText() + " is selected");
                }
                System.out.println(e.getText() + " is not selected");
            }

            driver.findElement(By.cssSelector("input[type='text'][name='firstname']")).sendKeys("James");
            driver.findElement(By.cssSelector("input[type='text'][name='lastname']")).sendKeys("watt");
            driver.findElement(By.cssSelector("input[type='text'][name='reg_email__']")).sendKeys("james.watt@fakemail.com");
            driver.findElement(By.cssSelector("input[type='text'][name='reg_email_confirmation__']")).sendKeys("james.watt@fakemail.com");
            driver.findElement(By.cssSelector("input[type='password'][data-type='password']")).sendKeys("123456789");

            WebElement selector = driver.findElement(By.id("month"));

            Select select = new Select(selector);
            select.selectByVisibleText("Jan");

            List<WebElement> monthlist = select.getOptions();

            System.out.println("months list has " + monthlist.size() + " months");

            for (int i = 0; i < monthlist.size(); i++) {
                System.out.println(monthlist.get(i).getText());
            }

            driver.findElement(By.cssSelector("input[type='radio'][value='2']")).click();

            System.out.println(driver.findElement(By.cssSelector("h2.mbs._3ma._6n._6s._6v")).getLocation());

            new Select(driver.findElement(By.id("day"))).selectByValue("12");
            new Select(driver.findElement(By.id("year"))).selectByIndex(15);

            System.out.println(driver.findElement(By.cssSelector("h2.inlineBlock._3ma._6n._6s._6v")).getText());

            element = driver.findElement(By.cssSelector("select#month"));


            driver.findElement(By.cssSelector("button[type='submit'][name='websubmit']")).submit();

            Point p = element.getLocation();

            System.out.println(p.x + ":" + p.y);

            driver.findElement(By.cssSelector("input[type='text'][name='reg_email_confirmation__']"));

            driver.get("http://toolsqa.com/automation-practice-form/");

            select = new Select(driver.findElement(By.cssSelector("select#selenium_commands")));


            if (select.isMultiple()) {
                System.out.println("This is a multi select");
            } else {
                System.out.println("This is not a multi select");
            }


            select.selectByVisibleText("Wait Commands");
            select.selectByVisibleText("Navigation Commands");
            Thread.sleep(5000);


//            Explicite wait

            driver.get("http://store.demoqa.com/");


            WebDriverWait Explicitwait = new WebDriverWait(driver, 20);
            WebElement e = Explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[alt='iPod Nano Blue'][width='500']")));

            boolean displayed = e.isDisplayed();

            if (displayed) {
                System.out.println("element is displayed");
                e.click();
                System.out.println("Element was clicked");
            } else {
                System.out.println("element was not displayed");
            }

//            Implicite Wait
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.navigate().back();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            System.out.println("navigated back ****");

            Thread.sleep(2000);

//            Fluent Wait

            driver.get("http://store.demoqa.com/products-page/product-category/macbooks/");

            Wait<WebDriver> FluentWait = new FluentWait<WebDriver>(driver)
                    .withTimeout(30, TimeUnit.SECONDS)
                    .pollingEvery(2, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);

            WebElement flE = FluentWait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    WebElement macbook = driver.findElement(By.xpath(".//a[contains(text(),'Apple 13-inch')]"));
                    if (macbook.isDisplayed()) {
                        System.out.println("macbook was displayed");
                    } else {
                        System.out.println("macbook was not displayed");
                    }


                    return macbook;
                }
            });


//            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//            driver.manage().timeouts().setScriptTimeout(2, TimeUnit.SECONDS);

            Thread.sleep(2000);

//            System.out.println(flE.getLocation());

            flE.click();

            driver.get("http://toolsqa.com/automation-practice-switch-windows/");

            String window = driver.getWindowHandle();

            driver.findElement(By.cssSelector("button#button1")).click();
            driver.switchTo().window(window);
            driver.findElement(By.cssSelector("button[onclick='newMsgWin()']")).click();
            driver.switchTo().window(window);
            driver.findElement(By.cssSelector("button[onclick='newBrwTab()']")).click();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Set<String> windowHandles = driver.getWindowHandles();

            for (String wh : windowHandles) {
                driver.switchTo().window(wh);
                System.out.println("Successfully switched to window " + wh);
            }

            driver.switchTo().window(window);
            driver.get("http://toolsqa.com/iframe-practice-page/");

            driver.get("http://toolsqa.com/handling-alerts-using-selenium-webdriver/");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.cssSelector("button[onclick='pushAlert()']")).click(); //

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Alert alert = driver.switchTo().alert();

            System.out.println("The message from the Alert is: " + alert.getText());

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            alert.accept();
            System.out.println("Accepted");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector("button[onclick='pushConfirm()']")).click();
            System.out.println("clicked");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            alert = driver.switchTo().alert();
            System.out.println(alert.getText());
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            alert.dismiss();
            System.out.println("Dismissed");


            WebElement elmt = driver.findElement(By.cssSelector("button[onclick='promptConfirm()']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);

            Alert promptAlert  = driver.switchTo().alert();
            String alertText = promptAlert .getText();
            System.out.println("Alert text is " + alertText);
            //Send some text to the alert
            promptAlert .sendKeys("Accepting the alert");
            Thread.sleep(4000); //This sleep is not necessary, just for demonstration
            promptAlert .accept();
            System.out.println("Text has been entered");

            Thread.sleep(2000);

            alert.accept();


        } catch (Exception e) {
            System.out.println("Test failed");
        } finally {
            driver.manage().deleteAllCookies();
            driver.close();
            driver.quit();
        }


    }

}
