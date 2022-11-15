package uz.pdp.companyproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    @NotNull(message = "companyName should not be null")
    private String companyName;

    @NotNull(message = "firstname should not be null")
    private String firstname;

    @NotNull(message = "lastname should not be null")
    private String lastname;

    @NotNull(message = "city should not be null")
    private String city;

    @NotNull(message = "street should not be null")
    private String street;

    @NotNull(message = "homeNumber should not be null")
    private String homeNumber;
}
