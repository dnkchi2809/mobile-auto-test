package Flow.Login;

import Action.Action;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.io.IOException;

public class WelcomeBack {
    public static void WelcomeBackContinue(AppiumDriver driver) throws InterruptedException, IOException {
        driver.findElement(By.id("android:id/button1")).click();

        Action.usingFingerprint(driver);
    }

    public static void WelcomeBackNotMe(AppiumDriver driver) {
        driver.findElement(By.id("android:id/button2")).click();
    }
}
