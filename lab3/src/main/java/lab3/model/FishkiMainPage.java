package lab3.model;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FishkiMainPage {
    private static final String PAGE_URL = "https://fishki.net";
    private final WebDriver driver;

    @Getter
    @FindBy(xpath = "//div[@class='drag_element']")
    private List<WebElement> posts;

    @Getter
    @FindBy(xpath = "//div[@class='header-user']")
    private WebElement userMenu;

    @FindBy(xpath = "//a[contains(@class, 'header-settings__link_to') and contains(@class, 'search')]")
    private WebElement showSearchButton;

    @FindBy(xpath = "//input[@class='search_input']")
    private WebElement searchInput;

    @FindBy(xpath = "//input[@class='search_input']/../button[@type='submit']")
    private WebElement searchButton;

    public FishkiMainPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void goToPost(int postIndex) {
        posts.get(postIndex).
                findElement(By.xpath("//div[@class='content__text']//a")).
                click();
    }

    public String getPostId(int postIndex) {
        return posts.get(postIndex).
                    findElement(By.xpath("//div[@class='content__text']//a")).
                    getAttribute("href").
                    split("-")[0].
                    split("net/")[1];
    }

    public String getPostTitle(int postIndex) {
        return posts.get(postIndex).
                findElement(By.xpath("//div[@class='content__text']//span[@itemprop='name']")).
                getAttribute("innerHTML").trim().replace("&nbsp", " ");
    }

    public void performSearch(String query) {
        showSearchButton.click();
        searchInput.sendKeys(query);
        searchButton.click();
    }
}
