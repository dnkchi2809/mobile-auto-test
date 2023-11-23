package Flow.Login;

import Action.Action;
import Notification.Notification;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.io.IOException;

public class WelcomeBack {
    public static void WelcomeBackContinue(AndroidDriver driver) throws InterruptedException, IOException {
        driver.findElement(By.id("android:id/button1")).click();

        Action.usingFingerprint();

        if (driver.findElement(By.id("com.android.permissioncontroller:id/content_container")).isDisplayed()) {
            Notification.Allow(driver);
        }
    }

    public static void WelcomeBackNotMe(AndroidDriver driver) {
        driver.findElement(By.id("android:id/button2")).click();
    }
}
