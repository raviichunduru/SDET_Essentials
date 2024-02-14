package com.tests;

import org.testng.annotations.Test;
import pojos.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCTest
{
  @Test
  public void JDBCTest () throws SQLException
  {
    String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";
    String username = "myusername";
    String password = "mypassword";

    Connection connection = DriverManager.getConnection(jdbcURL, username, password);
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("select * from \"user\" ; ");

    List<User> users = new ArrayList<>();

    while(resultSet.next())
    {
      users.add(new User(resultSet.getInt("id"), resultSet.getString("name")));
    }

    users.forEach(System.out::println);
  }
}
