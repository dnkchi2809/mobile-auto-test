import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;

public class TestRegister extends BaseTest {
    @Test
    public void test(){
        LaunchAppEvent launchAppEvent= new LaunchAppEvent(getDriver());
        launchAppEvent.test2();
    }
}
