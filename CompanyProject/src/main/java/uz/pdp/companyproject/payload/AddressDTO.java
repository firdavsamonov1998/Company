package uz.pdp.companyproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotNull(message = "city null bo'lishi mumkin emas")
    private String city;

    @NotNull(message = "steet null bo'lishi mumkin emas")
    private String street;

    @NotNull(message = "homeNumber null bo'lishi mumkin emas")
    private String homeNumber;
}
