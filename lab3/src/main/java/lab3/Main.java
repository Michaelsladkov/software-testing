package lab3;

import lab3.model.FishkiMainPage;
import lab3.model.FishkiPost;
import lab3.util.DriverManager;
import org.openqa.selenium.WebDriver;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = DriverManager.getInstance().getDrivers().findFirst().get();
        FishkiMainPage mainPage = new FishkiMainPage(driver);
        System.out.println(mainPage.getPosts().size());
        System.out.println(mainPage.getPostId(0));
        System.out.println(mainPage.getPostTitle(0));
        mainPage.goToPost(0);
        FishkiPost post = new FishkiPost(driver);
        System.out.println(post.getPostId());
        System.out.println(post.getTitle());
    }
}