import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MultiDriverMain {

    static AppiumDriverLocalService server1;
    static AppiumDriverLocalService server2;

    public static AppiumDriver driver1;

    public static AppiumDriver driver2;

    static void setInstance() {
        AppiumServiceBuilder builder1 = new AppiumServiceBuilder();
        builder1
                .usingPort(4723)
                .withIPAddress("127.0.0.1");
        server1 = AppiumDriverLocalService.buildService(builder1);

        AppiumServiceBuilder builder2 = new AppiumServiceBuilder();
        builder2
                .usingPort(4723)
                .withIPAddress("127.0.0.1");
        server2 = AppiumDriverLocalService.buildService(builder2);
    }

    static AppiumDriverLocalService[] getInstance() {
        if (server1 == null || server2 == null) {
            setInstance();
        }
        return new AppiumDriverLocalService[]{server1, server2};
    }

    public static void startAppiumServer() {
        getInstance()[0].start();
        System.out.println("startAppiumServer 1");
        System.out.println("URL: " + server1.getUrl());
        System.out.println("is Server running: " + server1.isRunning());

        getInstance()[1].start();
        System.out.println("startAppiumServe 2r");
        System.out.println("URL: " + server2.getUrl());
        System.out.println("is Server running: " + server2.isRunning());
    }

    public static void stopAppiumMultiServer() throws IOException {
        if (server1 == null) {
            getInstance()[0].stop();
            String deviceName = (String) driver1.getCapabilities().getCapability("deviceName");
            String cmd1 = "adb -s " + deviceName + " uninstall io.appium.uiautomator2.server";
            String cmd2 = "adb -s " + deviceName + " uninstall io.appium.uiautomator2.server.test";
            Runtime.getRuntime().exec(cmd1.split(" ", 0));
            Runtime.getRuntime().exec(cmd2.split(" ", 0));
            System.out.println("stopAppiumServer 1");
        }

        if (server2 == null) {
            getInstance()[1].stop();
            String deviceName = (String) driver2.getCapabilities().getCapability("deviceName");
            String cmd1 = "adb -s " + deviceName + " uninstall io.appium.uiautomator2.server";
            String cmd2 = "adb -s " + deviceName + " uninstall io.appium.uiautomator2.server.test";
            Runtime.getRuntime().exec(cmd1.split(" ", 0));
            Runtime.getRuntime().exec(cmd2.split(" ", 0));
            System.out.println("stopAppiumServer 2");
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            startAppiumServer();
            // Desired capabilities for the first device
            DesiredCapabilities capabilities1 = new DesiredCapabilities();
            capabilities1.setCapability("platformName", "Android");
            capabilities1.setCapability("udid", "emulator-5554");
            capabilities1.setCapability("automationName", "uiautomator2");
            capabilities1.setCapability("appPackage", "com.ziichat.android.media");
            capabilities1.setCapability("appActivity", "com.halome.media.app.MainActivity");
            // Add other capabilities as needed

            // Desired capabilities for the second device
            DesiredCapabilities capabilities2 = new DesiredCapabilities();
            capabilities2.setCapability("platformName", "Android");
            capabilities2.setCapability("udid", "emulator-5556");
            capabilities2.setCapability("automationName", "uiautomator2");
            capabilities2.setCapability("appPackage", "com.ziichat.android.media");
            capabilities2.setCapability("appActivity", "com.halome.media.app.MainActivity");
            // Add other capabilities as needed

            // Create Appium drivers for each device
            driver1 = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities1);
            driver2 = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities2);

            // Now you can perform actions on each driver independently
            // For example, interact with the chat app using driver1 and driver2

            // Close the drivers when done
            driver1.quit();
            driver2.quit();
        } finally {
            stopAppiumMultiServer();
            System.out.println("finish");
        }
    }
}
