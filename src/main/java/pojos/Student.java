package pojos;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import strategy.NameStrategy;
import uk.co.jemos.podam.common.PodamCollection;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;
import uk.co.jemos.podam.common.PodamStrategyValue;
import uk.co.jemos.podam.common.PodamStringValue;

import java.util.List;

@Data
public class Student
{
  //@PodamIntValue(minValue = 1, maxValue = 1000)
  @PodamExclude
  private int id;

  //@PodamStrategyValue(value = NameStrategy.class)
  @PodamExclude
  private String firstName;

  @PodamExclude
  private String lastName;

  private Address address;

  @PodamCollection(nbrElements = 2, collectionElementStrategy = NameStrategy.class)
  private List<String> jobs;

}
