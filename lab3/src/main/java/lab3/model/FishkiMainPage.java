package lab3.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FishkiMainPage {
    private static final String PAGE_URL = "https://fishki.net";
    private final WebDriver driver;

    public FishkiMainPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }
}
