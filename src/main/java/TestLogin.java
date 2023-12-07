import org.testng.annotations.Test;

public class TestLogin extends BaseTest{
    @Test
    public void test(){
        LaunchAppEvent launchAppEvent= new LaunchAppEvent(getDriver());
        launchAppEvent.test1();
    }
}
