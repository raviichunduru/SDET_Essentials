package com.utils.zerocell;

import com.creditdatamw.zerocell.converter.Converter;

public class StringToIntegerConverter implements Converter {
  @Override
  public Integer convert(String value, String columnname, int rownumber) {
    return Integer.parseInt(value);
  }
}
