package pages.googlehomepage;

public enum GoogleHomePageFieldName {
  TXT_FOR_GOOGLE_SEARCH_BUTTON("GoogleSearchButton"),
  TXT_FOR_FEELING_LUCKY_BUTTON("ForFeelingLuckyButton");

  private String value;

  GoogleHomePageFieldName(String value) {
    this.value = value;
  }

  public String getValue () {
    return value;
  }
}
