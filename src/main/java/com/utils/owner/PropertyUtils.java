package com.utils.owner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class PropertyUtils {
    /*
       too much of try catch block code.. even in finally where closing fis object
       if catch block not defined, we end up with NULL Pointer exception
    */

  private PropertyUtils() {
  }

  ;

  public static String readPropertyFile(String key) {
    Properties properties = null;
    FileInputStream fileInputStream = null;

    try {
      properties = new Properties();
      fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/FrameworkConfig.properties");
      properties.load(fileInputStream);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      //throw new RuntimeException("problem with file");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        fileInputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return properties.getProperty(key);
  }
}
