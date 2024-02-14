package com.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
import pojos.Employee;
import pojos.Student;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.io.File;
import java.io.IOException;

public class PODAMTest
{
  File file = new File(System.getProperty("user.dir") + "/src/test/resources/student.json");

  @Test
  public void PODAMTest () throws IOException
  {
    PodamFactory podamFactory = new PodamFactoryImpl();

    //populating all student properties using PODAM
    Student student1 = podamFactory.manufacturePojo(Student.class);
    System.out.println("student1 = " + student1);

    //below will read fields from json and for rest of fields nothing will be populated

    Student student2 = new ObjectMapper().readValue(file, Student.class);
    System.out.println("student2 = " + student2);

    // here we're populating id and firstname from json and remaining using PODAM
    Student student3 = podamFactory.populatePojo(student2);
    System.out.println("student3 = " + student3);

    // we can pass different data types for first and last names of Employee as we defined them as generic
    Employee employee = podamFactory.manufacturePojo(Employee.class, String.class, String.class);
    System.out.println("employee = " + employee);

  }
}
