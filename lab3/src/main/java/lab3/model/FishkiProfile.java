package lab3.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FishkiProfile {
    private final WebDriver driver;

    @FindBy(xpath = "(//div[@class='profile-user__buttons']//button)[1]")
    private WebElement myPostsButton;

    @FindBy(xpath = "(//div[@class='profile-user__buttons']//button)[3]")
    private WebElement myLikesButton;

    @FindBy(xpath = "(//div[@class='profile-user__buttons']//button)[5]")
    private WebElement myCommentsButton;

    public FishkiProfile(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='profile-user__buttons']//button)[1]")));
        PageFactory.initElements(driver, this);
    }

    public void goToLiked() {
        myLikesButton.click();
    }

    public void goToCommented() {
        myCommentsButton.click();
    }

    public void goToPosts() {
        myPostsButton.click();
    }

    public int getLikesNumber() {
        String numberStr = driver.findElement(By.xpath(
                "(//div[@class='profile-user__buttons']//button)[3]//span"
        )).getAttribute("innerHTML");
        return Integer.parseInt(numberStr);
    }

    public int getCommentsNumber() {
        String numberStr = driver.findElement(By.xpath(
                "(//div[@class='profile-user__buttons']//button)[5]//span"
        )).getAttribute("innerHTML");
        return Integer.parseInt(numberStr);
    }

    public int getPostsNumber() {
        String numberStr = driver.findElement(By.xpath(
                "(//div[@class='profile-user__buttons']//button)[1]//span"
        )).getAttribute("innerHTML");
        return Integer.parseInt(numberStr);
    }
}
