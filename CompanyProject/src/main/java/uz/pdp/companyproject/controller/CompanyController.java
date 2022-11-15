package uz.pdp.companyproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyproject.entity.Company;
import uz.pdp.companyproject.payload.CompanyDTO;
import uz.pdp.companyproject.payload.ResponseMessageDTO;
import uz.pdp.companyproject.service.CompanyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    /**
     * This method is used for adding new company data in DB
     *
     * @param companyDTO CompanyDto
     */
    @PostMapping("/add")
    private ResponseEntity<ResponseMessageDTO> add(@RequestBody @Valid CompanyDTO companyDTO) {
        return companyService.add(companyDTO);
    }


    /**
     * This method is used for getting all the company data in DB
     *
     * @return List of company data
     */
    @GetMapping("/get")
    private ResponseEntity<List<Company>> getAll() {
        return companyService.getAll();
    }


    /**
     * This method is used for getting the company data in DB
     *
     * @param id Long
     * @return Company data
     * <p>
     * if company data is not exist in DB return null
     */
    @GetMapping("/get/{id}")
    private ResponseEntity<Company> getById(@PathVariable Long id) {
        return companyService.getById(id);
    }


    /**
     * This method is used for editing the company data in DB
     *
     * @param id         Long
     * @param companyDTO CompanyDTO
     * @return ResponseMessageDTO
     */
    @PutMapping("/edite/{id}")
    private ResponseEntity<ResponseMessageDTO> edite(@PathVariable Long id, @RequestBody @Valid CompanyDTO companyDTO) {
        return companyService.edite(id, companyDTO);
    }


    /**
     * This method is used for deleting company data in DB
     *
     * @param id Long
     * @return ResponseMessageDTO
     */
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ResponseMessageDTO> delete(@PathVariable Long id) {
        return companyService.delete(id);
    }


}
