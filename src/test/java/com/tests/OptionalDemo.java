package com.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.Optional;

public class OptionalDemo {
  @Test
  public void handlingPopupWithOutOptional() {
    WebDriver chromeDriver = new ChromeDriver();
    chromeDriver.get("http://google.com");

    JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
    js.executeScript("alert('welcome');");

    try {
      chromeDriver.switchTo().alert().accept();
    } catch (NoAlertPresentException e) {
      System.out.println(e.getMessage());
      //e.printStackTrace();
    }

    chromeDriver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Google");
    chromeDriver.close();
  }

  @Test
  public void handlingPopupWithOptional() {
    WebDriver chromeDriver = new ChromeDriver();
    chromeDriver.get("http://google.com");

    JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
    js.executeScript("alert('welcome');");

    Optional.ofNullable(ExpectedConditions.alertIsPresent().apply(chromeDriver))
      .ifPresentOrElse(Alert::accept, () -> System.out.println("No alert present. continued with flow"));

    chromeDriver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Google");
    chromeDriver.close();
  }
}
