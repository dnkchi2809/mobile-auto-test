package Flow;

import Flow.Search.Search;
import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.net.MalformedURLException;

import static Flow.Chat.Chat.*;
import static Flow.Login.Login.loginZiiChat;
import static Flow.Search.Search.clickOnSearchResult;
import static Flow.Search.Search.goToSearchScreen;

public class TestFlow {
    public static void testFlow(AppiumDriver driver1, AppiumDriver driver2) throws IOException, InterruptedException {
        Thread device1Thread = new Thread(() -> {
            try {
                loginZiiChat(driver1, "chi.0001");

                goToSearchScreen(driver1);
                Search.searchWithKeyword(driver1, "chi.0002");
                clickOnSearchResult(driver1, 0);

                clickEditorInput(driver1);
                sendMessage(driver1, "device 1 send");

                clickBackToChannelList(driver1);
            } catch (IOException | InterruptedException e){
                throw new RuntimeException(e );
            }
        });

        Thread device2Thread = new Thread(() -> {
            try {
                loginZiiChat(driver2, "chi.0002");

                clickEnterChannel(driver2, 0);
                clickEditorInput(driver2);
                sendMessage(driver2, "device 2 send");

                clickBackToChannelList(driver2);
            } catch (IOException | InterruptedException e){
                throw new RuntimeException(e );
            }
        });

        device1Thread.start();
        device2Thread.start();

        try {
            device1Thread.join();
            device2Thread.join();
        } catch (InterruptedException e){
            e.printStackTrace(System.out);
        }

//        loginZiiChat(driver1, "chi.0001");
//        loginZiiChat(driver2, "chi.0002");
//
//        goToSearchScreen(driver1);
//        Search.searchWithKeyword(driver1, "chi.0002");
//        clickOnSearchResult(driver1, 0);
//
//        clickEditorInput(driver1);
//        sendMessage(driver1, "device 1 send");
//
//        clickEnterChannel(driver2, 0);
//        clickEditorInput(driver2);
//        sendMessage(driver2, "device 2 send");
//
//        clickBackToChannelList(driver1);
//        clickBackToChannelList(driver2);
    }
}
