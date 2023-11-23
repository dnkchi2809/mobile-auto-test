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

        driver.findElement(By.id("com.ziichat.android.media:id/buttonContinue")).click();

        driver.findElement(By.id("com.ziichat.android.media:id/btn_get_started")).click();

        driver.findElement(By.id("com.ziichat.android.media:id/txtUsername")).sendKeys("automobile.chi.0001");

        driver.findElement(By.id("com.ziichat.android.media:id/btnContinue")).click();

        if (driver.findElement(By.id("com.ziichat.android.media:id/alertTitle")).isDisplayed()) {
            WelcomeBack.WelcomeBackContinue(driver);
        }
    }
}
