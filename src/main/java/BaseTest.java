import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    public ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public void setDriver(AppiumDriver driver) {
        this.driver.set(driver);
    }

    public AppiumDriver getDriver(){
        return this.driver.get();
    }

    @BeforeMethod
    @Parameters({"deviceName", "platformName", "portNumber"})
    public void initialDriver(String deviceName, String platformName, String portNumber) throws MalformedURLException {
        startAppiumService(portNumber);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", deviceName);
        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("portNumber", portNumber);
        desiredCapabilities.setCapability("appPackage", "com.ziichat.android.media");
        desiredCapabilities.setCapability("appActivity", "com.halome.media.app.MainActivity");

        setDriver(new AndroidDriver(new URL("http://127.0.0.1:" + portNumber + "/wd/hub"), desiredCapabilities));
    }

    @AfterMethod
    public void closeDriver(){
        getDriver().quit();
    }

    public void startAppiumService(String portNumber){
        AppiumDriverLocalService service;
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(Integer.parseInt(portNumber));
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        System.out.println("Service start");
    }
}
