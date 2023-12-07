import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class TestLogin extends BaseTest{
    private RemoteWebDriver driver;

    @Parameters({"deviceName", "appPackage", "appActivity"})
    @BeforeTest
    public void setUp(String deviceName, String appPackage, String appActivity) throws MalformedURLException {
        driver = BaseTest.initializeAndroidDriver(deviceName, appPackage, appActivity);
    }

    @Test
    public void test1() {
        System.out.println("TestLogin");
    }
}
