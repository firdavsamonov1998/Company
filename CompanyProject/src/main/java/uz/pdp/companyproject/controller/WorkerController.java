package uz.pdp.companyproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyproject.entity.Worker;
import uz.pdp.companyproject.payload.ResponseMessageDTO;
import uz.pdp.companyproject.payload.WorkerDTO;
import uz.pdp.companyproject.service.WorkerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    /**
     * This method is used for adding department in DB
     *
     * @param workerDTO WorkerDTO
     * @return ResponseMessageDTO
     */
    @PostMapping("/add")
    private ResponseEntity<ResponseMessageDTO> add(@RequestBody @Valid WorkerDTO workerDTO) {
        return workerService.add(workerDTO);
    }

    /**
     * This method is used for getting all the department data in db
     *
     * @return List of Deparment data
     */
    @GetMapping("/get")
    private ResponseEntity<List<Worker>> getAll() {
        return workerService.getAll();
    }


    /**
     * This method is used for getting Department data by id in DB
     *
     * @param id Long
     * @return Department
     * <p>
     * if this data is not exist return null
     */
    @GetMapping("/get/{id}")
    private ResponseEntity<Worker> getById(@PathVariable Long id) {
        return workerService.getById(id);
    }

    /**
     * This method is used for editing Worker data
     *
     * @param id        Long
     * @param workerDTO WorkerDTO
     * @return ResponseMessageDTO
     */
    @PutMapping("/edite/{id}")
    private ResponseEntity<ResponseMessageDTO> edite(@PathVariable Long id, @RequestBody @Valid WorkerDTO workerDTO) {
        return workerService.edite(id, workerDTO);
    }


    /**
     * This method is used for deleting Worker data in DB
     *
     * @param id Long
     * @return ResponseMessageDTO
     */
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ResponseMessageDTO> delete(@PathVariable Long id) {
        return workerService.delete(id);
    }
}
