package com.utils.testdatasupplier;

import io.github.sskorol.converters.DefaultConverter;

public class StringToIntegerConverter extends DefaultConverter {
   /* @Override
    public Integer convert(String value, String columnname, int rownumber)
    {
        return Integer.parseInt(value);
    }*/

  @Override
  public Object convert(String s) {
    return Integer.parseInt(s);
  }

  @Override
  public Object convert(String value, String format) {
    return super.convert(value, format);
  }
}
