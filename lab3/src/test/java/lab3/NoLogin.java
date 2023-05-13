package lab3;

import lab3.model.FishkiMainPage;
import lab3.model.FishkiPost;
import lab3.model.FishkiSearchResults;
import lab3.util.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

public class NoLogin {

    private static Stream<Arguments> driverStream() {
        return DriverManager.getInstance().getDrivers().map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("driverStream")
    public void checkPostOpen(WebDriver d) {
        FishkiMainPage mainPage = new FishkiMainPage(d);
        String firstPostId = mainPage.getPostId(0);
        String firstPostTitle = mainPage.getPostTitle(0);
        mainPage.goToPost(0);
        FishkiPost post = new FishkiPost(d);
        Assertions.assertEquals(firstPostId, post.getPostId());
        Assertions.assertEquals(firstPostTitle, post.getTitle());
    }

    @ParameterizedTest
    @MethodSource("driverStream")
    public void checkSearch(WebDriver d) {
        FishkiMainPage mainPage = new FishkiMainPage(d);
        String secondPostId = mainPage.getPostId(1);
        String secondPostTitle = mainPage.getPostTitle(1);
        String[] titleWords = secondPostTitle.split("\\s");
        StringBuilder searchQuery = new StringBuilder();
        for (int i = 0; i < 5 && i < titleWords.length; ++i) {
            searchQuery.append(titleWords[i].toLowerCase());
            searchQuery.append(' ');
        }
        mainPage.performSearch(searchQuery.toString());
        FishkiSearchResults searchResults = new FishkiSearchResults(d);
        Assertions.assertEquals(secondPostId, searchResults.getPostId(0));
    }

    @AfterAll
    public static void exit() {
        DriverManager.getInstance().closeDrivers();
    }
}
