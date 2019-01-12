//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxBinary;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//
//import java.io.File;
//
//public class HeadlessFirefoxOprion {
//
//    public static void main(String[] args) {
//
//        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
//        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
//
//
//        WebDriver driver = null;
//
//        String os = System.getProperty("os.name").toLowerCase();
//
//        if(os.contains("mac")){
//            System.setProperty("webdriver.gecko.driver","Generic/Drivers/Mac/geckodriver");
//        }else if(os.contains("windows")){
//            System.setProperty("webdriver.gecko.driver","Generic/Drivers/Windows/geckodriver.exe");
//        }else{
//            System.setProperty("webdriver.gecko.driver","Generic/Drivers/Linux/geckodriver");
//        }
//
//        FirefoxBinary firefoxBinary = new FirefoxBinary(new File("C:\\Users\\sunsh\\AppData\\Local\\Mozilla Firefox\\firefox.exe"));
//        firefoxBinary.addCommandLineOptions("-headless");
//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        firefoxOptions.setBinary(firefoxBinary);
//        driver = new FirefoxDriver(firefoxOptions);
//
//
//        String url= "http://www.facebook.com";
//
//
//
//        try{
//            driver.get(url);
//
//            System.out.println(driver.getTitle());
//        }catch(Exception e){
//            System.out.println("Test Failed");
//        }finally {
//            driver.manage().deleteAllCookies();
//            driver.close();
//            driver.quit();
//        }
//    }
//}
