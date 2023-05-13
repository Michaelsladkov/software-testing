package lab3.model;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FishkiSearchResults {
    private final WebDriver driver;

    @Getter
    @FindBy(xpath = "//div[@class='drag_element']")
    private List<WebElement> posts;

    public FishkiSearchResults(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPostId(int postIndex) {
        return posts.get(postIndex).
                findElement(By.xpath("//div[@class='content__text']//a")).
                getAttribute("href").
                split("-")[0].
                split("net/")[1];
    }
}
