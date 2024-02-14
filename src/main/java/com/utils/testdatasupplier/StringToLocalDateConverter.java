package com.utils.testdatasupplier;

import io.github.sskorol.converters.DefaultConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateConverter extends DefaultConverter<LocalDate> {
  @Override
  public LocalDate convert(String value) {
    // Define the date pattern of your input string
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Parse the string to a LocalDate object using the defined pattern
    LocalDate localDate = LocalDate.parse(value, formatter);

    return localDate;
  }

  @Override
  public LocalDate convert(String value, String format) {
    return super.convert(value, format);
  }
}
