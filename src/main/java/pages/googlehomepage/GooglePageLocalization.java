package pages.googlehomepage;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import pojos.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import static pages.googlehomepage.GoogleHomePageByLocator.BY_FOR_FEELING_LUCKY_BUTTON;
import static pages.googlehomepage.GoogleHomePageByLocator.BY_FOR_GOOGLE_SEARCH_BUTTON;
import static pages.googlehomepage.GoogleHomePageFieldName.TXT_FOR_FEELING_LUCKY_BUTTON;
import static pages.googlehomepage.GoogleHomePageFieldName.TXT_FOR_GOOGLE_SEARCH_BUTTON;

public final class GooglePageLocalization {

  ChromeDriver driver;
  ChromeOptions chromeOptions;
  DevTools devTools;

  private static final Map<By, GoogleHomePageFieldName> elementInfo = Map.of(
    BY_FOR_GOOGLE_SEARCH_BUTTON, TXT_FOR_GOOGLE_SEARCH_BUTTON,
    BY_FOR_FEELING_LUCKY_BUTTON, TXT_FOR_FEELING_LUCKY_BUTTON);

  public static final GooglePageLocalization INSTANCE = new GooglePageLocalization();

  public static GooglePageLocalization getInstance() {
    return INSTANCE;
  }

  public GooglePageLocalization assertThatEachFieldLocalized(Locale locale) {
    for (Map.Entry<By, GoogleHomePageFieldName> entry : elementInfo.entrySet()) {
      By locator = entry.getKey();
      String expectedValue = getExpectedValue(locale, entry.getValue());
      String actualValue = getActualValue(locator);
      assertThatFieldNameIsLocalized(expectedValue, actualValue);
    }
    return this;
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

  public GooglePageLocalization navigateToGoogleHomePage() {
    driver.manage().deleteAllCookies();
    driver.get("https://www.google.com/");
    return this;
  }

  private String getActualValue(By by) {
    return driver.findElement(by).getAttribute("value");
  }

  public GooglePageLocalization changeLatitudeAndLongitude(Locale locale) {
    devTools = driver.getDevTools();
    devTools.createSession();
    devTools.send(Emulation.setGeolocationOverride(Optional.of(locale.getLatitude()), Optional.of(locale.getLongitude()),Optional.empty()));
    return this;
  }

  public GooglePageLocalization changeBrowserLocale(Locale locale) {
    chromeOptions = new ChromeOptions();
    chromeOptions.addArguments(String.format("--lang=%s", locale.getLocaleName()));
    driver = new ChromeDriver(chromeOptions);
    return this;
  }

  public void closeBrowser() {
    if(Objects.nonNull(driver))
      driver.quit();
  }
}
