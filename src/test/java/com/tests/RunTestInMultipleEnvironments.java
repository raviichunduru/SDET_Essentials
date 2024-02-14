package com.tests;

import com.utils.multipleenvironment.FrameWorkConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;

public class RunTestInMultipleEnvironments {
  @Test
  public void RunTestInMultipleEnvironments() {
    FrameWorkConfig config = ConfigFactory.create(FrameWorkConfig.class);

    System.out.println(config.browser());
    System.out.println(config.username());
    System.out.println(config.url());
  }
}
