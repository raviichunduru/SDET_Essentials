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

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static io.github.sskorol.data.TestDataReader.use;

public class LocalizationTest
{
  ChromeDriver driver;
  ChromeOptions chromeOptions;
  DevTools devTools;

  private static final Map<By, String> elementLocatorAndName = Map.of(
    By.xpath("//div[@class='lJ9FBc']//input[@name='btnK']"), "GoogleSearchButton",
    By.xpath("//div[@class='FPdoLc lJ9FBc']//input[@name='btnI']"), "ForFeelingLuckyButton");

  @BeforeTest()
  void setUp() {
  }

  @Test(dataProvider = "getLocaleInfo")
  public void localizationTest (Locale locale) {

     changeBrowserLocale(locale)
      .changeLatitudeAndLongitude(locale)
      .openBrowser()
      .assertThatEachFieldLocalized(locale);
  }

  private void assertThatEachFieldLocalized(Locale locale) {
    for (Map.Entry<By, String> entry : elementLocatorAndName.entrySet()) {
      By locator = entry.getKey();
      String expectedValue = getExpectedValue(locale, entry.getValue());
      String actualValue = getActualValue(locator);
      assertThatFieldNameIsLocalized(expectedValue, actualValue);
    }
  }

  private String getExpectedValue(Locale locale, String fieldName) {
    switch (fieldName) {
      case "GoogleSearchButton":
        return locale.getLocalizedNameForGoogleSearchButton();
      case "ForFeelingLuckyButton":
        return locale.getLocalizedNameForFeelingLuckyButton();
      default:
        throw new IllegalArgumentException("Unknown field: " + fieldName);
    }
  }

  private static void assertThatFieldNameIsLocalized(String expectedValue, String actualValue) {
    Assertions.assertThat(actualValue).isEqualTo(expectedValue);
  }

  private LocalizationTest openBrowser() {
    driver.manage().deleteAllCookies();
    driver.get("https://www.google.com/");
    return this;
  }

  private String getActualValue(By by) {
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

  @DataSupplier(runInParallel = false)
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
