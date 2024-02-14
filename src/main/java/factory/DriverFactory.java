package factory;

import enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class DriverFactory {
  private DriverFactory() {
  }

  ;

  public static WebDriver getDriver(BrowserType browserType) {
    WebDriver driver = null;

    if (BrowserType.CHROME.equals(browserType))
      return new ChromeDriver();

    else if (BrowserType.FIREFOX.equals(browserType))
      return new FirefoxDriver();

    else
      throw new RuntimeException("Pass proper browser type in excel");
  }
}
