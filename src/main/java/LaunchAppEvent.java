import io.appium.java_client.AppiumDriver;

public class LaunchAppEvent {
    AppiumDriver driver;

    public LaunchAppEvent(AppiumDriver driver) {
        this.driver = driver;
    }

    public void test1() {
        System.out.println("TestLogin");
    }

    public void test2() {
        System.out.println("TestRegister");
    }
}
