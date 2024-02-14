package com.tests.owner;

import com.utils.owner.FrameWorkConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class RunnerWithOwner {
  public static void main(String[] args) {
    FrameWorkConfig config = ConfigFactory.create(FrameWorkConfig.class);
    System.out.println("config.browser() = " + config.browser());

    WebDriver chrome = new ChromeDriver();
    chrome.get("http://google.com");
    chrome.manage().timeouts().implicitlyWait(Duration.ofMillis(config.timeout()));
    chrome.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Google");

    config.favtools().forEach(a -> System.out.println(a));

    if (config.takescreenshot()) {
      System.out.println("screenshot taken");
    }
    chrome.quit();
  }
}
