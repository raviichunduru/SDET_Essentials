package pojos;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Data()
public class Locale {
  String localeName;
  Number latitude;
  Number longitude;
  String localizedNameForGoogleSearchButton;
}
