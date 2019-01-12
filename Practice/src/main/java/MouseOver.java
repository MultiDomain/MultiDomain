//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//
//public class MouseOver {
//
//    public static void main(String[] args) {
//        WebDriver driver = null;
//
//        String os = System.getProperty("os.name").toLowerCase();
//
//        if(os.contains("mac")){
//            System.setProperty("webdriver.chrome.driver","Generic/Drivers/chromedriver");
//        }else if(os.contains("windows")){
//            System.setProperty("webdriver.chrome.driver","Generic/Drivers/chromedriver.exe");
//        }else{
//            System.setProperty("webdriver.chrome.driver","Generic/Drivers/chromedriverlx");
//        }
//
//        driver = new ChromeDriver();
//
//
//        try{
//            String url = "http://store.demoqa.com/";
//            driver.get(url);
//            WebElement element = driver.findElement(By.xpath(".//a[contains(text(),'Product Category')]"));
//
//            Actions action = new Actions(driver);
////            action.moveToElement(element).moveToElement(driver.findElement(By.xpath(".//*[contains(text(),'iPhones')]"))).click().build().perform();
////
////            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
////
////            driver.navigate().back();
////
////            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//            action.moveToElement(element).perform();
//
//            WebElement secEle = driver.findElement(By.xpath(".//*[contains(text(),'iPhones')]"));
//            action.moveToElement(secEle);
//
//            action.click().perform();
//
//            Thread.sleep(5000);
//
//        }catch (Exception e){
//            System.out.println("Test Failed");
//        }
//        finally {
//            driver.manage().deleteAllCookies();
//            driver.close();
//            driver.quit();
//        }
//    }
//}
