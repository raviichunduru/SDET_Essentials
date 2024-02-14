package com.utils.testdatasupplier;

import enums.BrowserType;
import io.github.sskorol.converters.DefaultConverter;

public class StringToBrowserTypeConverter extends DefaultConverter<BrowserType> {

   /* @Override
    public BrowserType convert(String value, String columnname, int rownumber)
    {
        return BrowserType.valueOf(value.toUpperCase());
    }*/

  @Override
  public BrowserType convert(String value) {
    return BrowserType.valueOf(value.toUpperCase());
  }

}
