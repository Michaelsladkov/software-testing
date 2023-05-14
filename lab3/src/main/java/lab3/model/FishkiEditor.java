package lab3.model;

import lab3.util.TestProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

public class FishkiEditor {

    private final WebDriver driver;

    @FindBy(xpath = "//div[contains(@class, 'editor__content')]")
    private WebElement editorContent;

    public FishkiEditor(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addPost(String headerText, String announceText, String imageUrl) throws EditorException, InterruptedException {
        WebElement headerArea = editorContent.findElement(By.xpath("//h1"));
        Actions actions = new Actions(driver);
        actions.moveToElement(headerArea).perform();
        actions.click().perform();
        actions.sendKeys(headerText).perform();
        WebElement announceArea = editorContent.findElement(By.xpath("//p"));
        actions.moveToElement(announceArea).perform();
        actions.click().perform();
        actions.sendKeys(announceText).perform();
        actions.sendKeys(Keys.RETURN).perform();
        WebElement addButton = driver.findElement(By.xpath(
                "//div[contains(@class, 'editor__menu')]//button"
        ));
        actions.moveToElement(addButton).perform();
        actions.click().perform();
        WebElement addImageButton = driver.findElement(By.xpath(
                "(//div[contains(@class, 'expanded-content')]//button)[1]"
        ));
        actions.moveToElement(addImageButton);
        actions.click().perform();
        WebElement imageUrlField = driver.findElement(By.xpath(
                "//div[@class='fishki-uploader']//input[@type='text']"
        ));
        imageUrlField.sendKeys(imageUrl);
        imageUrlField.sendKeys(Keys.RETURN);
        Thread.sleep(2000);
        List<WebElement> alerts= driver.findElements(By.xpath(
                "//div[@class='fishki-uploader']//div[@role='alert']"
        ));
        if (!alerts.isEmpty()) {
            imageUrlField.clear();
            announceArea.clear();
            headerArea.clear();
            Thread.sleep(5000);
            throw new EditorException(
                    EditorException.Cause.TOO_LOW_IMAGE_RESOLUTION,
                    alerts.get(0).getAttribute("innerHTML")
            );
        }
        WebElement publishButton = driver.findElement(By.xpath(
                "//button[@class='v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default primary']"
        ));
        Actions clickPostButton = new Actions(driver);
        clickPostButton.moveToElement(publishButton);
        clickPostButton.click();
        clickPostButton.perform();
        Thread.sleep(2000);
        WebElement categorySelect = driver.findElement(By.xpath(
                "(//div[contains(@class, 'menu-publication')]//div[@class='v-select__slot'])[1]"
        ));
        categorySelect.click();
        Thread.sleep(700);
        driver.findElement(By.xpath("(//div[@role='option'])[1]")).click();
        WebElement tagsInput = driver.findElement(By.xpath(
                "(//div[contains(@class, 'menu-publication')]//div[@class='v-select__slot'])[3]//" +
                        "div[@class='v-select__selections']//input[@type='text']/.."
        ));
        WebElement ownRadio = driver.findElement(By.xpath("//input[@type='radio' and @value='own']"));
        Actions setPostToOwn = new Actions(driver);
        setPostToOwn.moveToElement(ownRadio);
        setPostToOwn.click();
        setPostToOwn.perform();
        Actions enterTags = new Actions(driver);
        enterTags.moveToElement(tagsInput);
        enterTags.click();
        enterTags.sendKeys(TestProperties.getProp("tag1")).sendKeys(",").sendKeys(" ").perform();
        enterTags.sendKeys(TestProperties.getProp("tag2")).sendKeys(",").sendKeys(" ").perform();
        enterTags.sendKeys(TestProperties.getProp("tag3")).sendKeys(Keys.RETURN).perform();
        WebElement savePostButton = driver.findElement(By.xpath(
                "(//div[contains(@class, 'v-list-item')]//button//span)[1]/.."
        ));
        savePostButton.click();
    }
}
