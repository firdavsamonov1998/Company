package uz.pdp.companyproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.companyproject.entity.Address;
import uz.pdp.companyproject.entity.Department;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDTO {

    @NotNull(message = "firstname should not be null")
    private String firstname;

    @NotNull(message = "lastname should not be null")
    private String lastname;

    @NotNull(message = "phone should not be null")
    @Min(value = 13, message = "phone length should be 13")
    @Max(value = 13, message = "phone length should be 13")
    private String phone;

    @NotNull(message = "city should not be null")
    private String city;

    @NotNull(message = "street should not be null")
    private String street;

    @NotNull(message = "homeNumber should not be null")
    private String homeNumber;

    @NotNull(message = "department id should not be null")
    private Long departMentId;
}
