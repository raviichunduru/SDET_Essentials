package com.utils.zerocell;

import com.creditdatamw.zerocell.converter.Converter;

public class StringToBooleanConverter implements Converter {
  @Override
  public Boolean convert(String value, String columnname, int rownumber) {
    return value.equalsIgnoreCase("true");
  }
}
