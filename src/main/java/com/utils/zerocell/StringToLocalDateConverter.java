package com.utils.zerocell;

import com.creditdatamw.zerocell.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateConverter implements Converter<LocalDate> {
  @Override
  public LocalDate convert(String value, String columnname, int rownumber) {
    // Define the date pattern of your input string
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Parse the string to a LocalDate object using the defined pattern
    LocalDate localDate = LocalDate.parse(value, formatter);

    return localDate;
  }
}
