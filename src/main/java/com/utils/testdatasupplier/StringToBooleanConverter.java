package com.utils.testdatasupplier;

import io.github.sskorol.converters.DefaultConverter;

public class StringToBooleanConverter extends DefaultConverter {

  @Override
  public Object convert(String value) {
    return value.equalsIgnoreCase("true");
  }

  @Override
  public Object convert(String value, String format) {
    return super.convert(value, format);
  }
}
