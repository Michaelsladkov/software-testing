package lab3;

import lab3.model.*;
import lab3.util.DriverManager;
import lab3.util.TestProperties;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    /*
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
    }*/

    @ParameterizedTest
    @MethodSource("driverStream")
    @Order(5)
    public void like(WebDriver d) throws InterruptedException {
        FishkiMainPage mainPage = new FishkiMainPage(d);
        mainPage.goToProfile();
        FishkiProfile profile = new FishkiProfile(d);
        int initialLikes = profile.getLikesNumber();
        mainPage = new FishkiMainPage(d);
        String postId = mainPage.getPostId(2);
        mainPage.goToPost(2);
        FishkiPost post = new FishkiPost(d);
        post.setLike();
        mainPage = new FishkiMainPage(d);
        mainPage.goToProfile();
        profile = new FishkiProfile(d);
        Assertions.assertEquals(initialLikes + 1, profile.getLikesNumber());
        profile.goToLiked();
        Thread.sleep(1500);
        d.findElement(By.xpath("(//div[@class='drag_element']//div[@class='content__text']//a)[1]")).click();
        post = new FishkiPost(d);
        Assertions.assertEquals(postId, post.getPostId());
        post.resetLike();
        mainPage = new FishkiMainPage(d);
        mainPage.goToProfile();
        profile = new FishkiProfile(d);
        Assertions.assertEquals(initialLikes, profile.getLikesNumber());
    }

    @ParameterizedTest
    @MethodSource("driverStream")
    @Order(6)
    public void comment(WebDriver d) throws InterruptedException {
        FishkiMainPage mainPage = new FishkiMainPage(d);
        String postId = mainPage.getPostId(3);
        mainPage.goToPost(3);
        FishkiPost post = new FishkiPost(d);
        post.postComment(TestProperties.getProp("sampleCommentText"));
        Thread.sleep(1000);
        mainPage = new FishkiMainPage(d);
        Thread.sleep(1000);
        mainPage.goToProfile();
        FishkiProfile profile = new FishkiProfile(d);
        Assertions.assertEquals(1, profile.getCommentsNumber());
        profile.goToCommented();
        Thread.sleep(2000);
        String commentText = d.findElement(By.xpath(
                "//div[contains(@class, 'comment__text')]")).getAttribute("innerHTML").trim();
        Assertions.assertEquals(TestProperties.getProp("sampleCommentText"), commentText);
        WebElement postLink = d.findElement(By.xpath("//a[@class='goto_post_class']"));
        d.get(postLink.getAttribute("href"));
        post = new FishkiPost(d);
        Assertions.assertEquals(postId, post.getPostId());
        mainPage = new FishkiMainPage(d);
        mainPage.goToProfile();
        profile = new FishkiProfile(d);
        profile.goToCommented();
        WebElement deleteButton = d.findElement(By.xpath("//a[@class='chat-user__delete']"));
        ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
        Actions actions = new Actions(d);
        actions.moveToElement(deleteButton).perform();
        actions.click().perform();
        WebElement confirmDelete = d.findElement(By.xpath("//a[contains(@class, 'btn-comment-remove')]"));
        actions.moveToElement(confirmDelete).perform();
        actions.click().perform();
        Thread.sleep(1000);
        mainPage = new FishkiMainPage(d);
        mainPage.goToProfile();
        profile = new FishkiProfile(d);
        Assertions.assertEquals(0, profile.getCommentsNumber());
    }

    @ParameterizedTest
    @MethodSource("driverStream")
    @Order(7)
    public void likeDislikeComment(WebDriver d) {
        int commentId = 1;
        int postIndex = 12;
        FishkiMainPage mainPage = new FishkiMainPage(d);
        String postId = mainPage.getPostId(postIndex);
        mainPage.goToPost(postIndex);
        String commentRatingXPath = "(//div[@class='small-likes-wrap'])[" + commentId + "]//div[contains(@class, 'likes-count')]";
        String commentLikeButtonXpath = "((//div[@class='small-likes-wrap'])[" + commentId + "]/*[name()='svg'])[1]";
        String commentDislikeButtonXpath = "((//div[@class='small-likes-wrap'])[" + commentId + "]/*[name()='svg'])[2]";
        WebElement firstCommentLikeButton = d.findElement(By.xpath(commentLikeButtonXpath));
        WebElement firstCommentDislikeButton = d.findElement(By.xpath(commentDislikeButtonXpath));
        WebElement firstCommentRating = d.findElement(By.xpath(commentRatingXPath));
        Actions actions = new Actions(d);
        String ratingStr = firstCommentRating.getAttribute("innerHTML").trim();
        int initialRating = Integer.parseInt(ratingStr);
        ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(true);", firstCommentDislikeButton);

        actions.moveToElement(firstCommentLikeButton);
        actions.click().perform();

        firstCommentRating = d.findElement(By.xpath(commentRatingXPath));
        ratingStr = firstCommentRating.getAttribute("innerHTML").trim();
        Assertions.assertEquals(initialRating + 1, Integer.parseInt(ratingStr));

        firstCommentLikeButton = d.findElement(By.xpath(commentLikeButtonXpath));
        actions.moveToElement(firstCommentLikeButton);
        actions.click().perform();
        firstCommentRating = d.findElement(By.xpath(commentRatingXPath));
        ratingStr = firstCommentRating.getAttribute("innerHTML").trim();
        Assertions.assertEquals(initialRating + 1, Integer.parseInt(ratingStr));
    }

    @AfterAll
    public static void exit() {
        DriverManager.getInstance().closeDrivers();
    }
}
