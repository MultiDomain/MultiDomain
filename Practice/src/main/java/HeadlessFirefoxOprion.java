import org.openqa.selenium.WebDriver;

public class HeadlessFirefoxOprion {

    public static void main(String[] args) {

        WebDriver driver = null;

        String os = System.getProperty("os.name").toLowerCase();

        if(os.contains("mac")){
            System.setProperty("webdriver.gecko.driver","Generic/Drivers/Mac/geckodriver");
        }else if(os.contains("windows")){
            System.setProperty("webdriver.chrome.driver","Generic/Drivers/Windows/geckodriver.exe");
        }else{
            System.setProperty("webdriver.chrome.driver","Generic/Drivers/Linux/geckodriver");
        }


    }
}
