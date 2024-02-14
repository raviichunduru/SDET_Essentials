package com.tests;

import com.machinezoo.noexception.Exceptions;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NoExceptionDemo {
  //@SneakyThrows
  @Test
  public void normalException() {
    // for a simple operation of finding a file and work book.. we may encountger 2 exceptions. keeping them with try catch blocks add more verbose.

    try {
      FileInputStream fis = new FileInputStream("");
      XSSFWorkbook workbook = new XSSFWorkbook(fis);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void withNoExceptionLibrary() {
    // here we're wrapping checked exceptions with Runtime exception...with no verbose.

    FileInputStream fileInputStream = Exceptions.wrap(RuntimeException::new).get(() -> new FileInputStream(""));
  }


}
