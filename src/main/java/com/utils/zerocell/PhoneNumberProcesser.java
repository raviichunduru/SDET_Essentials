package com.utils.zerocell;

import com.creditdatamw.zerocell.converter.Converter;

public class PhoneNumberProcesser implements Converter<String> {
  @Override
  public String convert(String value, String columnname, int rownumber) {
    //Conditional if statement.. evaluating if value is starting with +91 or not
    // if starting with +91, return as-is...else prefix +91 and return

    return value.startsWith("+91") ? value : "+91" + value;
  }
}
