package Flow.Chat;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Chat {

    public static void clickEnterChannel(AppiumDriver appiumDriver, int index) {
        List<WebElement> userResultList = appiumDriver.findElements(By.id("com.ziichat.android.media:id/containerItemChannel"));
        userResultList.get(index).click();
    }

    public static void clickBackToChannelList(AppiumDriver appiumDriver) {
        appiumDriver.findElement(By.xpath("//android.widget.ImageButton[@index=0]")).click();
    }

    public static void clickEditorInput(AppiumDriver appiumDriver) {
        appiumDriver.findElement(By.id("com.ziichat.android.media:id/editTextMessage")).click();
    }

    public static void sendMessage(AppiumDriver appiumDriver, String messageTxt) {
        appiumDriver.findElement(By.id("com.ziichat.android.media:id/editTextMessage")).sendKeys(messageTxt);

        appiumDriver.findElement(By.id("com.ziichat.android.media:id/btnSendMessage")).click();
    }
}
