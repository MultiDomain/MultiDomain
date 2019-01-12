//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Action;
//import org.openqa.selenium.interactions.Actions;
//
//import java.util.concurrent.TimeUnit;
//
//public class DragAndDrop {
//    static WebDriver driver;
//
//    public static void main(String[] args) {
//
//        String os = System.getProperty("os.name").toLowerCase();
//
//        if (os.contains("mac")) {
//            System.setProperty("webdriver.chrome.driver", "../Generic/Drivers/chromedriver");
//        } else if (os.contains("windows")) {
//            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/chromedriver.exe");
//        } else {
//            System.setProperty("webdriver.chrome.driver", "../Generic/Drivers/chromedriverlx");
//        }
//
//        try {
//            driver = new ChromeDriver();
//            driver.manage().deleteAllCookies();
//
//            try {
//                driver.manage().window().maximize();
//            } catch (Exception x) {
//                driver.manage().window().fullscreen();
//            }
//
//
//            String url = "http://demo.guru99.com/test/drag_drop.html";
//            driver.get(url);
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//            System.out.println(driver.getTitle());
//            System.out.println(driver.getCurrentUrl());
//            Thread.sleep(5000);
//            WebElement from, to;
//            from = driver.findElement(By.cssSelector("li.block13.ui-draggable"));
//            to = driver.findElement(By.cssSelector("ol.field13.ui-droppable.ui-sortable#amt7 > li"));
//            Actions builder = new Actions(driver);
//            Action dragAnddrop = builder.clickAndHold(from).moveToElement(to).release().build();
//            dragAnddrop.perform();
//
//            Thread.sleep(5000);
//
//        } catch (Exception ex) {
//            System.out.println("Test failed");
//        } finally {
//            driver.manage().deleteAllCookies();
//            driver.close();
//            driver.quit();
//        }
//
//    }
//
//}
