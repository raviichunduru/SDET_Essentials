package com.tests.zerocell;

import com.utils.zerocell.ExcelReader;
import com.utils.zerocell.TestData;
import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ZeroCellTests {
  @Test(dataProvider = "getData")
  public void testcase1(TestData testData) {
    WebDriver driver = DriverFactory.getDriver(testData.getBrowser());
    driver.get("http://google.com");
    driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys(testData.getFirstname());
    driver.close();
  }

  @DataProvider(parallel = true)
  public Object[] getData(Method method) {
    return ExcelReader.getTestData()
      .stream()
      .filter(a -> a.getTestcase().equalsIgnoreCase(method.getName()))
      .toArray();
  }
}
