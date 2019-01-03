//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class Practice {
//
//    public static void main(String[] args) {
//
//        WebDriver driver = null;
//        String os = System.getProperty("os.name").toLowerCase();
//
//        if (os.contains("mac")) {
//            System.setProperty("webdriver.chrome.driver", "Generic/Driver/Mac/chromedriver");
//        } else if (os.contains("windows")) {
//            System.setProperty("webdriver.chrome.driver", "Generic/Driver/Windows/chromedriver.exe");
//        } else {
//            System.setProperty("webdriver.chrome.driver", "Generic/Drivers/Linux/chromedriver");
//        }
//
//        driver = new ChromeDriver();
//
//        try{
//            String url = "";
//            driver.get(url);
//
//        }catch(Exception e){
//
//        }finally {
//            driver.manage().deleteAllCookies();
//            driver.close();
//            driver.quit();
//        }
//
//    }
//}
