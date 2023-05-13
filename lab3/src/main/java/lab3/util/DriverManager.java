package lab3.util;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DriverManager {
    private final List<WebDriver> drivers = new ArrayList<>();

    private DriverManager(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        FirefoxDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        drivers.add(chromeDriver);
        drivers.add(firefoxDriver);
    }

    private static DriverManager instance = null;

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public void closeDrivers() {
        getDrivers().forEach(WebDriver::close);
    }

    public Stream<WebDriver> getDrivers() {
        return drivers.stream();
    }
}
