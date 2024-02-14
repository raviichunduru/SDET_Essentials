package com.utils.owner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.util.Objects.isNull;

public class TryWithResources {
  private TryWithResources() { } ;

  private static Properties properties = new Properties();
  private static Map<String, String> CONFIG_MAP = new HashMap<>();

  static {
    try (FileInputStream fileInputStream = new FileInputStream(
      System.getProperty("user.dir") + "/src/test/resources/FrameworkConfig.properties")) {
      properties.load(fileInputStream);
      for (Map.Entry<Object, Object> entry : properties.entrySet()) {
        CONFIG_MAP.put((String) entry.getKey(), (String) entry.getValue());
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(0);
    }
  }

  public static String getValue(ConfigProperties key) {
    if (isNull(key) || isNull(CONFIG_MAP.get(key.name().toLowerCase()))) {
      System.out.println("Either Property key" + key + " is not available or its value is null. check property config file");
    }
    return CONFIG_MAP.get(key.name().toLowerCase());
  }
}
