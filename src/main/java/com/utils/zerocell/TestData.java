package com.utils.zerocell;

import com.creditdatamw.zerocell.annotation.Column;
import enums.BrowserType;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class TestData {
  //POJO

  @Column(name = "testcase", index = 0)
  private String testcase;

  @Column(name = "browser", index = 1, converterClass = StringToBrowserTypeConverter.class)
  private BrowserType browser;

  @Column(name = "firstname", index = 2)
  private String firstname;

  @Column(name = "isFTE", index = 3, converterClass = StringToBooleanConverter.class)
  private boolean IsFTE;

  @Column(name = "age", index = 4, converterClass = StringToIntegerConverter.class)
  private int age;

  @Column(name = "date", index = 5, converterClass = StringToLocalDateConverter.class)
  private LocalDate date;

  @Column(name = "phonenumber", index = 6, converterClass = PhoneNumberProcesser.class)
  private String phonenumber;
}
