package lab3;

import lab3.model.*;
import lab3.util.DriverManager;
import lab3.util.TestProperties;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FishkiTests {

    private static Stream<Arguments> driverStream() {
        return DriverManager.getInstance().getDrivers().map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("driverStream")
    @Order(1)
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
    @Order(2)
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
    @Order(3)
    public void login(WebDriver d) throws InterruptedException {
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
        String login = TestProperties.getProp("login");
        String password = TestProperties.getProp("password");
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password.repeat(20));
        WebDriverWait wait  = new WebDriverWait(d, Duration.ofSeconds(5));
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@class='form-auth']/button")));
        WebElement loginButton = d.findElement(By.xpath("//form[@class='form-auth']/button"));
        loginButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@class='form-auth']/div[@class='error']")));
        List<WebElement> errorMessages = d.findElements(By.xpath("//form[@class='form-auth']/div[@class='error']"));
        WebElement errorMessage = errorMessages.isEmpty() ? null : errorMessages.get(0);
        Assertions.assertNotNull(errorMessage);
        loginInput.clear();
        passwordInput.clear();
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginButton.click();
        Thread.sleep(3000);
        WebElement profileLink = d.findElement(By.xpath("//a[contains(@class, 'icon__avatar')]"));
        Assertions.assertNotNull(profileLink);
        Assertions.assertEquals(TestProperties.getProp("TestProfileUrl"), profileLink.getAttribute("href"));
    }

    @ParameterizedTest
    @MethodSource("driverStream")
    @Order(4)
    public void post(WebDriver d) throws InterruptedException {
        FishkiMainPage mainPage = new FishkiMainPage(d);
        mainPage.goToEditor();
        Thread.sleep(5000);
        FishkiEditor editor = new FishkiEditor(d);
        boolean catchException = false;
        try {
            editor.addPost(
                    TestProperties.getProp("samplePostHeader"),
                    TestProperties.getProp("samplePostAnnounce"),
                    TestProperties.getProp("lowQualityImage"));
        } catch (EditorException e) {
            catchException = true;
            Assertions.assertEquals(e.getEditorCause(), EditorException.Cause.TOO_LOW_IMAGE_RESOLUTION);
        }
        Assertions.assertTrue(catchException);
        try {
            editor.addPost(
                    TestProperties.getProp("samplePostHeader"),
                    TestProperties.getProp("samplePostAnnounce"),
                    TestProperties.getProp("normalImage"));
        } catch (EditorException e) {
            Assertions.fail();
        }
        d.get(TestProperties.getProp("fishkiURL"));
        d.switchTo().alert().accept();
        mainPage = new FishkiMainPage(d);
        mainPage.goToProfile();
        FishkiProfile profile = new FishkiProfile(d);
        Assertions.assertEquals(1, profile.getPostsNumber());
        profile.goToPosts();
        Thread.sleep(700);
        WebElement postLink = d.findElement(By.xpath(
                "(//div[@class='drag_element']//div[@class='content__text']//a)[1]"
        ));
        postLink.click();
        WebElement deleteButton = d.findElement(By.xpath("//a[contains(@class,'post-delete')]"));
        deleteButton.click();
        Thread.sleep(1500);
        WebElement confirmDelete = d.findElement(By.xpath("//a[contains(@id, 'post-delete-do')]"));
        confirmDelete.click();
        d.get(TestProperties.getProp("fishkiURL"));
        mainPage = new FishkiMainPage(d);
        mainPage.goToProfile();
        profile = new FishkiProfile(d);
        Assertions.assertEquals(0, profile.getPostsNumber());
    }

    @ParameterizedTest
    @MethodSource("driverStream")
    @Order(5)
    public void like(WebDriver d) throws InterruptedException {
        FishkiMainPage mainPage = new FishkiMainPage(d);
        String postId = mainPage.getPostId(2);
        mainPage.goToPost(2);
        FishkiPost post = new FishkiPost(d);
        post.setLike();
        mainPage = new FishkiMainPage(d);
        mainPage.goToProfile();
        FishkiProfile profile = new FishkiProfile(d);
        Assertions.assertEquals(1, profile.getLikesNumber());
        profile.goToLiked();
        Thread.sleep(1500);
        d.findElement(By.xpath("(//div[@class='drag_element']//div[@class='content__text']//a)[1]")).click();
        post = new FishkiPost(d);
        Assertions.assertEquals(postId, post.getPostId());
        post.resetLike();
        mainPage = new FishkiMainPage(d);
        mainPage.goToProfile();
        profile = new FishkiProfile(d);
        Assertions.assertEquals(0, profile.getLikesNumber());
    }

    @AfterAll
    public static void exit() {
        DriverManager.getInstance().closeDrivers();
    }
}
