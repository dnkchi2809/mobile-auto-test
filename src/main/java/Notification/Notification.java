package Notification;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.io.IOException;

public class Notification {
    public static void Allow(AndroidDriver driver) throws InterruptedException, IOException {
        Thread.sleep(2000);
        driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
    }

    public static void NotAllow(AndroidDriver driver) throws InterruptedException, IOException {
        Thread.sleep(2000);
        driver.findElement(By.id("com.android.permissioncontroller:id/permission_deny_button")).click();
    }
}
