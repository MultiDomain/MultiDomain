//import com.gargoylesoftware.htmlunit.BrowserVersion;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//
//import java.util.concurrent.TimeUnit;
//
//
//public class headlessBeowser {
//    public static void main(String[] args) {
//
//        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
//        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
//
//
////        String os = System.getProperty("os.name").toLowerCase();
////
////        if (os.contains("mac")) {
////            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/Mac/chromedriver");
////        } else if (os.contains("windows")) {
////            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/Windows/chromedriver.exe");
////        } else {
////            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/Linux/chromedriver");
////        }
//
//        HtmlUnitDriver unitDriver = new HtmlUnitDriver();
//        unitDriver.setJavascriptEnabled(true);
//
//        try {
//
//            unitDriver.get("http://google.com");
//
//            System.out.println("Title of the page is -> " + unitDriver.getTitle());
//
//            WebElement searchBox = unitDriver.findElement(By.name("q"));
//
//            searchBox.sendKeys("Selenium", Keys.ENTER);
//
//
//
////            WebElement button = unitDriver.findElement(By.name("btnK"));
////
////            button.click();
//
//            System.out.println("Title of the page is -> " + unitDriver.getTitle());
//
//            String title = (String) unitDriver.executeAsyncScript("return document.title");
//
//        }
//        catch (Exception ex){
//            System.out.println("Test Failed");
//        }finally {
//            unitDriver.manage().deleteAllCookies();
//            unitDriver.close();
//            unitDriver.quit();
//        }
//
//    }
//
//}
