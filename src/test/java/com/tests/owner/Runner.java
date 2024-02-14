package com.tests.owner;

import com.utils.owner.PropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Runner {

    /*

    Issues with this Runner Class

    reading property file multiple times
    reading every value as string,, explicitly parsing.. unnecessary code


     */

  public static void main(String[] args) {
    String browser = PropertyUtils.readPropertyFile("browser");
    System.out.println("browser = " + browser);

    WebDriver chrome = new ChromeDriver();
    chrome.get("http://google.com");
    String timeout = PropertyUtils.readPropertyFile("timeout");
    chrome.manage().timeouts().implicitlyWait(Duration.ofMillis(Long.parseLong(timeout)));

    chrome.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Google");

    String favtools = PropertyUtils.readPropertyFile("favtools");
    List<String> favtoollist = List.of(favtools.split(","));
    for (String s : favtoollist) {
      System.out.println(s);
    }

    if (PropertyUtils.readPropertyFile("takescreenshot").equalsIgnoreCase("true")) {
      System.out.println("screenshot taken");
    }
    chrome.quit();
  }
}
