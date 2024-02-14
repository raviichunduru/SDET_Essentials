package com.tests.testdatasupplier;

import com.utils.testdatasupplier.TestData;
import com.utils.testdatasupplier.User;
import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.CsvReader;
import io.github.sskorol.data.JsonReader;
import io.github.sskorol.data.XlsxReader;
import one.util.streamex.StreamEx;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

import static io.github.sskorol.data.TestDataReader.use;

public class UserTest {
  @Test(dataProvider = "getDataFromJson")
  public void printUsersFromJson(User user) {
    System.out.println(user);
  }

  @DataSupplier
  public StreamEx<User> getDataFromJson() {
    return use(JsonReader.class)
      .withTarget(User.class)
      .withSource("testdatasupplier_testdata.json")
      .read();
  }

  @Test(dataProvider = "getDataFromCsv")
  public void printUsersFromCsv(User user) {
    System.out.println(user);
  }

  @DataSupplier
  public StreamEx<User> getDataFromCsv() {
    return use(CsvReader.class)
      .withTarget(User.class)
      .withSource("testdatasupplier_testdata.csv")
      .read();
  }

  @Test(dataProvider = "getDataFromExcel")
  public void testcase1(TestData testData) {
    System.out.println(testData);
  }

  @DataSupplier
  public StreamEx<TestData> getDataFromExcel(Method method) {
    return use(XlsxReader.class)
      .withTarget(TestData.class)
      .withSource("testdatasupplier_testdata.xlsx")
      .read()
      .filter(testData -> testData.getTestcase().equalsIgnoreCase(method.getName()));
  }
}
