package Flow.Login;

import Action.Action;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.io.IOException;

public class Login {

    public static void swipeIntro(AndroidDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        Action.swipeToLeft(driver);
        Thread.sleep(1500);
        Action.swipeToLeft(driver);

    }
    public static void loginZiiChat(AndroidDriver driver) throws IOException, InterruptedException {
        swipeIntro(driver);

        Thread.sleep(4000);
        driver.findElement(By.id("com.ziichat.android.media:id/buttonContinue")).click();

        Thread.sleep(2000);
        driver.findElement(By.id("com.ziichat.android.media:id/btn_get_started")).click();

        Thread.sleep(3000);
        driver.findElement(By.id("com.ziichat.android.media:id/txtUsername")).sendKeys("automobile.chi.0001");

        Thread.sleep(2000);
        driver.findElement(By.id("com.ziichat.android.media:id/btnContinue")).click();

        Thread.sleep(3000);
        if (driver.findElement(By.id("com.ziichat.android.media:id/alertTitle")).isDisplayed()) {
            WelcomeBack.WelcomeBackContinue(driver);
        }
    }
}
