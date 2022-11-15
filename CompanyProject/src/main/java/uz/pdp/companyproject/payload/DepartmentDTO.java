package uz.pdp.companyproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    @NotNull(message = "department should not be null")
    private String departmentName;

    @NotNull(message = "companyId should not be null")
    private Long companyId;
}
