package com.utils.owner;

import org.aeonbits.owner.Config;

import java.util.List;

@Config.Sources(value = "file:${user.dir}/src/test/resources/FrameworkConfig.properties")
public interface FrameWorkConfig extends Config {
  @DefaultValue("CHROME")
  BrowserType browser();

  @DefaultValue("20")
  long timeout();

  @DefaultValue("true")
  boolean takescreenshot();

  List<String> favtools();
}
