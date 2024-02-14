package com.utils.testdatasupplier;

import enums.BrowserType;
import io.github.sskorol.data.Column;
import io.github.sskorol.data.Sheet;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@ToString
@Sheet(name = "Sheet1")
public class TestData {
  //POJO

  @Column(name = "testcase")
  private String testcase;

  @Column(name = "browser", converter = StringToBrowserTypeConverter.class)
  private BrowserType browser;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "isFTE", converter = StringToBooleanConverter.class)
  private boolean IsFTE;

  @Column(name = "age", converter = StringToIntegerConverter.class)
  private int age;

  @Column(name = "date", converter = StringToLocalDateConverter.class)
  private LocalDate date;

  @Column(name = "phonenumber", converter = PhoneNumberProcesser.class)
  private String phonenumber;

  @Column(name = "skills", converter = StringToListConverter.class)
  private List<String> skills;
}
