package lab3;

import lab3.model.FishkiMainPage;
import lab3.model.FishkiPost;
import lab3.model.FishkiSearchResults;
import lab3.util.DriverManager;
import lab3.util.TestProperties;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class FishkiTests {

    private static Stream<Arguments> driverStream() {
        return DriverManager.getInstance().getDrivers().map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("driverStream")
    public void postOpen(WebDriver d) {
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
    public void search(WebDriver d) {
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

    @ParameterizedTest
    @MethodSource("driverStream")
    public void login(WebDriver d) {
        FishkiMainPage mainPage = new FishkiMainPage(d);
        WebElement loginLink = d.findElement(By.xpath("//a[@href='/user/login/']"));
        loginLink.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement loginInput = d.findElement(By.xpath("//input[@name='userlogin']"));
        WebElement passwordInput = d.findElement(By.xpath("//input[@name='userpassword']"));
        WebElement loginButton = d.findElement(By.xpath("//form[@class='form-auth']/button"));
        String login = TestProperties.getProp("login");
        String password = TestProperties.getProp("password");
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password.repeat(2));
        loginButton.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement errorMessage = d.findElement(By.xpath("//form[@class='form-auth']/div[@class='error']"));
        Assertions.assertNotNull(errorMessage);
        loginInput.clear();
        passwordInput.clear();
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginButton.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement profileLink = d.findElement(By.xpath("//a[contains(@class, 'icon__avatar')]"));
        Assertions.assertNotNull(profileLink);
        Assertions.assertEquals(TestProperties.getProp("TestProfileUrl"), profileLink.getAttribute("href"));
    }

    @AfterAll
    public static void exit() {
        DriverManager.getInstance().closeDrivers();
    }
}
