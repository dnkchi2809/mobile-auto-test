import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;

public class TestRegister extends BaseTest {

    AppiumDriver appiumDriver;

    @Test
    public void test(){
        System.out.println("TestRegister");

        appiumDriver = getDriver();

    }
}
