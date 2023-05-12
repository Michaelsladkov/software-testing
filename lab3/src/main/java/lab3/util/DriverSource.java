package lab3.util;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DriverSource {
    private final List<WebDriver> drivers = new ArrayList<>();

    private DriverSource(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
        FirefoxDriver firefoxDriver = new FirefoxDriver();

        drivers.add(chromeDriver);
        drivers.add(firefoxDriver);
    }

    private static DriverSource instance = null;

    public static DriverSource getInstance() {
        if (instance == null) {
            instance = new DriverSource();
        }
        return instance;
    }

    public Stream<WebDriver> getDrivers() {
        return drivers.stream();
    }
}
