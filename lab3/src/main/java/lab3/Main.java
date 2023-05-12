package lab3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://fishki.net");
        System.out.println(driver.getTitle());

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement postList = driver.findElement(By.xpath("//div[@class='drag_list']"));
        List<WebElement> posts = postList.findElements(By.xpath("//div[@class='drag_element']"));
        System.out.println(posts.size());
        WebElement postLink = posts.get(0).findElement(By.xpath("//div[contains(@class, 'expanded-post')]"));
        System.out.println(postLink.getAttribute("data-post-id"));
    }
}