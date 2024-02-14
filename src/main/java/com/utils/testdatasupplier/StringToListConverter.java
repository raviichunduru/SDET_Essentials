package com.utils.testdatasupplier;

import io.github.sskorol.converters.DefaultConverter;

import java.util.Arrays;
import java.util.List;

public class StringToListConverter extends DefaultConverter<List<String>> {
  @Override
  public List<String> convert(String value) {
    return Arrays.asList(value.split(","));
  }

  @Override
  public List<String> convert(String value, String format) {
    return super.convert(value, format);
  }
}
