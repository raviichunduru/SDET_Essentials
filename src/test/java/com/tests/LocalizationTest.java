package com.tests;

import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.JsonReader;
import one.util.streamex.StreamEx;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.googlehomepage.GooglePageLocalization;
import pojos.Locale;

import java.util.Objects;

import static io.github.sskorol.data.TestDataReader.use;

public class LocalizationTest
{
  @Test(dataProvider = "getLocaleInfo")
  public void validatingLocalizationForGoogleHomePage(Locale locale) {

    GooglePageLocalization.getInstance()
      .changeBrowserLocale(locale)
      .changeLatitudeAndLongitude(locale)
      .navigateToGoogleHomePage()
      .assertThatEachFieldLocalized(locale)
      .closeBrowser();
  }

  @DataSupplier(runInParallel = false)
  public  StreamEx<Locale> getLocaleInfo() {
    return use(JsonReader.class)
      .withTarget(Locale.class)
      .withSource("locale.json")
      .read();
  }
}
