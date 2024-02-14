package com.tests;

import org.jdbi.v3.core.Jdbi;
import org.testng.annotations.Test;
import pojos.User;

import java.util.List;

public class JdbiTest
{
  @Test
  public void JdbiTest()
  {
    String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";
    String username = "myusername";
    String password = "mypassword";

    Jdbi jdbi = Jdbi.create(jdbcURL, username, password);

    // use useHandle when we don't need to return query result
    jdbi.useHandle(handle->handle.createQuery("select * from \"user\" ; ").mapToBean(User.class).forEach(System.out::println));

    // use withHandle when we need to return query result
    List<User> users = jdbi.withHandle(handle -> handle.createQuery("select * from \"user\" ; ")
      .mapToBean(User.class).list());

    users.forEach(System.out::println);
  }
}
