package uz.pdp.companyproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.companyproject.entity.Company;
import uz.pdp.companyproject.entity.Department;
import uz.pdp.companyproject.payload.DepartmentDTO;
import uz.pdp.companyproject.payload.ResponseMessageDTO;
import uz.pdp.companyproject.repository.CompanyRepository;
import uz.pdp.companyproject.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, CompanyRepository companyRepository) {
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }


    /**
     * This mthod is used for adding department data in DB
     *
     * @param departmentDTO DepartmentDTO
     * @return ResponsMessageDTO
     */
    public ResponseEntity<ResponseMessageDTO> add(DepartmentDTO departmentDTO) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDTO.getCompanyId());
        if (optionalCompany.isEmpty()) return ResponseEntity.status(404).body(
                new ResponseMessageDTO("Company data not founded. Please add Company data", false));

        Company company = optionalCompany.get();
        Department department = new Department();
        department.setDepartmentName(department.getDepartmentName());
        department.setCompany(company);

        departmentRepository.save(department);

        return ResponseEntity.status(200).body(
                new ResponseMessageDTO("Successfully saved", true));
    }

    /**
     * This method is used for getting all the department data in DB
     *
     * @return Department
     */
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.status(200).body(departmentRepository.findAll());
    }


    /**
     * This method is used for getting the department data by id
     *
     * @param id Long
     * @return Department
     * if id is not exist return null
     */
    public ResponseEntity<Department> getById(Long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(404).body(null);
        return ResponseEntity.status(200).body(optional.get());
    }


    /**
     * This method is used for editing department data in DB
     *
     * @param id            Long
     * @param departmentDTO DepartmnetDTO
     * @return ResponseMessageDTO
     */
    public ResponseEntity<ResponseMessageDTO> edite(Long id, DepartmentDTO departmentDTO) {
        Optional<Department> optional = departmentRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(404).body(
                new ResponseMessageDTO("This department not founded", false));
        Department department = optional.get();
        Company company = department.getCompany();
        department.setCompany(company);
        department.setDepartmentName(department.getDepartmentName());
        departmentRepository.save(department);

        return ResponseEntity.status(200).body(new ResponseMessageDTO("Successfully edited", true));
    }

    /**
     * This method is used for deleting department data in DB
     *
     * @param id Long
     * @return ResponseMessageDTO
     */
    public ResponseEntity<ResponseMessageDTO> delete(Long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(404).body(
                new ResponseMessageDTO("Not founded", false));
        departmentRepository.delete(optional.get());

        return ResponseEntity.status(200).body(new ResponseMessageDTO("Successfully deleted", true));
    }

}
