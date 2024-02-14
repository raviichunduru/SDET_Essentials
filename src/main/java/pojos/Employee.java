package pojos;

import lombok.Builder;
import lombok.Data;

@Data
public class Employee<T>
{
  private T firstName;
  private T lastName;
}
