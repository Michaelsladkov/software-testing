package lab3.model;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FishkiPost {
    private final WebDriver driver;

    @Getter
    @FindBy(xpath = "//h1[contains(@class, 'content__title')]")
    private WebElement titleElement;

    @FindBy(xpath = "(//div[@class='content__reactions']//div//button)[1]")
    private WebElement likeButton;

    public FishkiPost(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPostId() {
        return driver.getCurrentUrl().split("-")[0].split(".net/")[1];
    }

    public String getTitle() {
        return titleElement.getAttribute("innerHTML").trim().replace("&nbsp;", " ");
    }
}
