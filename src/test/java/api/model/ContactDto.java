package api.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

// импортировали все

public class ContactDto {
    String firstName;
    String lastName;
    String description;

}
