import Flow.TestFlow;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class MultiDriverMain {

    public static AppiumDriver driver1;

    public static AppiumDriver driver2;

    public static AppiumDriver initAppiumDriver(String deviceName) throws MalformedURLException {
        AppiumDriver driver;
        DesiredCapabilities capabilities1 = new DesiredCapabilities();
        capabilities1.setCapability("platformName", "Android");
        capabilities1.setCapability("udid", deviceName);
        capabilities1.setCapability("automationName", "uiautomator2");
        capabilities1.setCapability("appPackage", "com.ziichat.android.media");
        capabilities1.setCapability("appActivity", "com.halome.media.app.MainActivity");
        capabilities1.setCapability("autoGrantPermissions", "true");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities1);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return driver;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            Thread thread1 = new Thread(() -> {
                try {
                    driver1 = initAppiumDriver("emulator-5554");
                }catch (MalformedURLException e){
                    throw new RuntimeException(e );
                }
            });

            Thread thread2 = new Thread(() -> {
                try {
                    driver2 = initAppiumDriver("emulator-5556");
                }catch (MalformedURLException e){
                    throw new RuntimeException(e );
                }
            });

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e){
                e.printStackTrace(System.out);
            }

            // Now you can perform actions on each driver independently
            // For example, interact with the chat app using driver1 and driver23
            TestFlow.testFlow(driver1, driver2);

            // Close the drivers when done
            driver1.quit();
            driver2.quit();
        } finally {
            System.out.println("finish");
        }
    }
}
