import Flow.TestFlow;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class MultiDriverMain {

    public static AppiumDriver driver1;

    public static AppiumDriver driver2;

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            // Desired capabilities for the first device
            DesiredCapabilities capabilities1 = new DesiredCapabilities();
            capabilities1.setCapability("platformName", "Android");
            capabilities1.setCapability("udid", "emulator-5554");
            capabilities1.setCapability("automationName", "uiautomator2");
            capabilities1.setCapability("appPackage", "com.ziichat.android.media");
            capabilities1.setCapability("appActivity", "com.halome.media.app.MainActivity");
            capabilities1.setCapability("autoGrantPermissions", "true");
            // Add other capabilities as needed

            // Desired capabilities for the second device
            DesiredCapabilities capabilities2 = new DesiredCapabilities();
            capabilities2.setCapability("platformName", "Android");
            capabilities2.setCapability("udid", "emulator-5556");
            capabilities2.setCapability("automationName", "uiautomator2");
            capabilities2.setCapability("appPackage", "com.ziichat.android.media");
            capabilities2.setCapability("appActivity", "com.halome.media.app.MainActivity");
            capabilities2.setCapability("autoGrantPermissions", "true");
            // Add other capabilities as needed

            // Create Appium drivers for each device
            driver1 = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities1);
            driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            driver2 = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities2);
            driver2.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

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
