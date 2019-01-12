//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.PageFactory;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.TimeUnit;
//
//public class cacheLookupInPageObjectModel {
//
//    String os = System.getProperty("os.name").toLowerCase();
//    String url = "http://toolsqa.com/automation-practice-form/";
//
//    @Test
//    public void TestFirstNameAndLastName() {
//
//        String os = System.getProperty("os.name").toLowerCase();
//        String url = "http://toolsqa.com/automation-practice-form/";
//
//        if (os.contains("mac")) {
//            System.setProperty("webdriver.chrome.driver", "../Generic/Drivers/chromedriver");
//        } else if (os.contains("windows")) {
//            System.setProperty("webdriver.chrome.driver", "../Generic/Drivers/chromedriver.exe");
//        } else {
//            System.setProperty("webdriver.chrome.driver", "../Generic/Drivers/chromedriverlx");
//        }
//
//            System.setProperty("webdriver.chrome.logfile", "TestLog.txt");
//
//        WebDriver driver = new ChromeDriver();
//        driver.get(url);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//
//        PracticeFromPageObjectModel pageObject = PageFactory.initElements(driver, PracticeFromPageObjectModel.class);
//        pageObject.firstname.sendKeys("Alexander");
//        pageObject.lastname.sendKeys("Hamilton");
//
//        pageObject.firstname.getText();
//        pageObject.lastname.getText();
//
//        driver.close();
//        driver.quit();
//    }
//}
//
//
