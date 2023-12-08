package Flow.Login;

import Action.Action;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static Flow.Login.WelcomeBack.WelcomeBackContinue;

public class Login {
    public static void swipeIntro(AppiumDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        Action.swipeToLeft(driver);
        Thread.sleep(1500);
        Action.swipeToLeft(driver);

    }
    public static void loginZiiChat(AppiumDriver driver, String account) throws IOException, InterruptedException {
//        swipeIntro(driver);

        driver.findElement(By.id("com.ziichat.android.media:id/buttonContinue")).click();

        driver.findElement(By.id("com.ziichat.android.media:id/btn_get_started")).click();

        WebElement usernameInput = driver.findElement(By.id("com.ziichat.android.media:id/txtUsername"));

        usernameInput.sendKeys(account);

        driver.findElement(By.id("com.ziichat.android.media:id/btnContinue")).click();

        WelcomeBackContinue(driver);
    }
}
