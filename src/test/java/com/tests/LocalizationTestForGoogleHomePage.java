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
import pages.googlehomepage.GoogleHomePageFieldName;
import pojos.Locale;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static io.github.sskorol.data.TestDataReader.use;
import static pages.googlehomepage.GoogleHomePageByLocator.BY_FOR_FEELING_LUCKY_BUTTON;
import static pages.googlehomepage.GoogleHomePageByLocator.BY_FOR_GOOGLE_SEARCH_BUTTON;
import static pages.googlehomepage.GoogleHomePageFieldName.TXT_FOR_FEELING_LUCKY_BUTTON;
import static pages.googlehomepage.GoogleHomePageFieldName.TXT_FOR_GOOGLE_SEARCH_BUTTON;

public class LocalizationTestForGoogleHomePage
{
  ChromeDriver driver;
  ChromeOptions chromeOptions;
  DevTools devTools;

  private static final Map<By, GoogleHomePageFieldName> elementInfo = Map.of(
    BY_FOR_GOOGLE_SEARCH_BUTTON, TXT_FOR_GOOGLE_SEARCH_BUTTON,
    BY_FOR_FEELING_LUCKY_BUTTON, TXT_FOR_FEELING_LUCKY_BUTTON);

  @BeforeTest()
  void setUp() {
  }

  @Test(dataProvider = "getLocaleInfo")
  public void validatingLocalizationForGoogleHomePage(Locale locale) {

     changeBrowserLocale(locale)
      .changeLatitudeAndLongitude(locale)
      .openBrowser()
      .assertThatEachFieldLocalized(locale);
  }

  private void assertThatEachFieldLocalized(Locale locale) {
    for (Map.Entry<By, GoogleHomePageFieldName> entry : elementInfo.entrySet()) {
      By locator = entry.getKey();
      String expectedValue = getExpectedValue(locale, entry.getValue());
      String actualValue = getActualValue(locator);
      assertThatFieldNameIsLocalized(expectedValue, actualValue);
    }
  }

  private String getExpectedValue(Locale locale, GoogleHomePageFieldName fieldName) {
    switch (fieldName) {
      case TXT_FOR_GOOGLE_SEARCH_BUTTON:
        return locale.getLocalizedNameForGoogleSearchButton();
      case TXT_FOR_FEELING_LUCKY_BUTTON:
        return locale.getLocalizedNameForFeelingLuckyButton();
      default:
        throw new IllegalArgumentException("Unknown field: " + fieldName);
    }
  }

  private static void assertThatFieldNameIsLocalized(String expectedValue, String actualValue) {
    Assertions.assertThat(actualValue).isEqualTo(expectedValue);
  }

  private LocalizationTestForGoogleHomePage openBrowser() {
    driver.manage().deleteAllCookies();
    driver.get("https://www.google.com/");
    return this;
  }

  private String getActualValue(By by) {
    return driver.findElement(by).getAttribute("value");
  }

  private LocalizationTestForGoogleHomePage changeLatitudeAndLongitude(Locale locale) {
    devTools = driver.getDevTools();
    devTools.createSession();
    devTools.send(Emulation.setGeolocationOverride(Optional.of(locale.getLatitude()), Optional.of(locale.getLongitude()),Optional.empty()));
    return this;
  }

  private LocalizationTestForGoogleHomePage changeBrowserLocale(Locale locale) {
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
