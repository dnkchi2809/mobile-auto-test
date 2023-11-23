package Flow.Login;

import Action.Action;
import Notification.Notification;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.io.IOException;

public class WelcomeBack {
    public static void WelcomeBackContinue(AndroidDriver driver) throws InterruptedException, IOException {
        Thread.sleep(2000);
        driver.findElement(By.id("android:id/button1")).click();

        Action.usingFingerprint();

        Thread.sleep(2000);
        if (driver.findElement(By.id("com.android.permissioncontroller:id/content_container")).isDisplayed()) {
            Notification.Allow(driver);
        }
    }

    public static void WelcomeBackNotMe(AndroidDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("android:id/button2")).click();
    }
}
