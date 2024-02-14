package com.utils.zerocell;

import com.creditdatamw.zerocell.converter.Converter;
import enums.BrowserType;

public class StringToBrowserTypeConverter implements Converter<BrowserType> {

  @Override
  public BrowserType convert(String value, String columnname, int rownumber) {
    return BrowserType.valueOf(value.toUpperCase());
  }
}
