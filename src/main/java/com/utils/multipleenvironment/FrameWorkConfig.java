package com.utils.multipleenvironment;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
  "system:env",
  "file:${user.dir}/src/test/resources/dummyConfig.properties"})
public interface FrameWorkConfig extends Config {
  @DefaultValue("dev")
  String environment();

  String browser();

  @Key("${environment}.username")
  String username();

  @Key("${environment}.url")
  String url();
}