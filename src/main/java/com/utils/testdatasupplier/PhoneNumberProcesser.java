package com.utils.testdatasupplier;

import io.github.sskorol.converters.DefaultConverter;

public class PhoneNumberProcesser extends DefaultConverter<String> {

  @Override
  public String convert(String value) {
    //Conditional if statement.. evaluating if value is starting with +91 or not
    // if starting with +91, return as-is...else prefix +91 and return

    return value.startsWith("+91") ? value : "+91" + value;
  }

  @Override
  public String convert(String value, String format) {
    return super.convert(value, format);
  }
}