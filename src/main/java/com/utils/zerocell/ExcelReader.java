package com.utils.zerocell;

import com.creditdatamw.zerocell.Reader;

import java.io.File;
import java.util.List;

public final class ExcelReader {
  private ExcelReader() {
  }

  ;

  private static List<TestData> testData = null;

  public static List<TestData> getTestData() {
    return testData;
  }

  static {
    testData = Reader.of(TestData.class)
      .from(new File("zerocell_testdata.xlsx"))
      .sheet("Sheet1")
      .skipHeaderRow(true)
      .list();
  }

}
