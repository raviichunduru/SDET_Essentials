package com.tests;

import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.JsonReader;
import one.util.streamex.StreamEx;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojos.Locale;

import java.util.Objects;
import java.util.Optional;

import static io.github.sskorol.data.TestDataReader.use;

public class LocalizationTest
{
  ChromeDriver driver;
  ChromeOptions chromeOptions;
  DevTools devTools;
  String actualValue;

  private static final By GOOGLE_SEARCH_BUTTON = By.xpath("//div[@class='lJ9FBc']//input[@name='btnK']");

  @BeforeTest()
  void setUp() {
  }

  @Test(dataProvider = "getLocaleInfo")
  public void localizationTest (Locale locale) {

    actualValue = changeBrowserLocale(locale)
      .changeLatitudeAndLongitude(locale)
      .openBrowser()
      .getLocalizedNameForElement(GOOGLE_SEARCH_BUTTON);

    String expectedValue = locale.getLocalizedNameForGoogleSearchButton();
    assertThatFieldNameIsLocalized(expectedValue, actualValue);
  }

  private static void assertThatFieldNameIsLocalized(String expectedValue, String actualValue) {
    Assertions.assertThat(actualValue).isEqualTo(expectedValue);
  }

  private LocalizationTest openBrowser() {
    driver.manage().deleteAllCookies();
    driver.get("https://www.google.com/");
    return this;
  }

  private String getLocalizedNameForElement(By by) {
    return driver.findElement(by).getAttribute("value");
  }

  private LocalizationTest changeLatitudeAndLongitude(Locale locale) {
    devTools = driver.getDevTools();
    devTools.createSession();
    devTools.send(Emulation.setGeolocationOverride(Optional.of(locale.getLatitude()), Optional.of(locale.getLongitude()),Optional.empty()));
    return this;
  }

  private LocalizationTest changeBrowserLocale(Locale locale) {
    chromeOptions = new ChromeOptions();
    chromeOptions.addArguments(String.format("--lang=%s", locale.getLocaleName()));
    driver = new ChromeDriver(chromeOptions);
    return this;
  }

  @DataSupplier
  public  StreamEx<Locale> getLocaleInfo() {
    return use(JsonReader.class)
      .withTarget(Locale.class)
      .withSource("locale.json")
      .read();
  }

  @AfterTest
  void tearDown() {
    if(Objects.nonNull(driver))
    driver.quit();
  }
}
