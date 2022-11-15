package uz.pdp.companyproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyproject.entity.Department;
import uz.pdp.companyproject.payload.DepartmentDTO;
import uz.pdp.companyproject.payload.ResponseMessageDTO;
import uz.pdp.companyproject.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * This mthod is used for adding department data in DB
     *
     * @param departmentDTO DepartmentDTO
     * @return ResponsMessageDTO
     */
    @PostMapping("/add")
    private ResponseEntity<ResponseMessageDTO> add(@RequestBody @Valid DepartmentDTO departmentDTO) {
        return departmentService.add(departmentDTO);
    }


    /**
     * This method is used for getting all the department data in DB
     *
     * @return Department
     */
    @GetMapping("/get")
    private ResponseEntity<List<Department>> getAll() {
        return departmentService.getAll();
    }


    /**
     * This method is used for getting the department data by id
     *
     * @param id Long
     * @return Department
     * if id is not exist return null
     */
    @GetMapping("/get/{id}")
    private ResponseEntity<Department> getById(@PathVariable Long id) {
        return departmentService.getById(id);
    }


    /**
     * This method is used for editing department data in DB
     *
     * @param id            Long
     * @param departmentDTO DepartmnetDTO
     * @return ResponseMessageDTO
     */
    @PutMapping("/ediet/{id}")
    private ResponseEntity<ResponseMessageDTO> edite(@PathVariable Long id, @RequestBody @Valid DepartmentDTO departmentDTO) {
        return departmentService.edite(id, departmentDTO);
    }


    /**
     * This method is used for deleting department data in DB
     *
     * @param id Long
     * @return ResponseMessageDTO
     */
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ResponseMessageDTO> delete(@PathVariable Long id) {
        return departmentService.delete(id);
    }

}
