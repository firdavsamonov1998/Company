package uz.pdp.companyproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyproject.entity.Director;
import uz.pdp.companyproject.payload.DirectorDTO;
import uz.pdp.companyproject.payload.ResponseMessageDTO;
import uz.pdp.companyproject.service.DirectorService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/director")
public class DirectorController {

    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }


    /**
     * This method is used for adding Director data in DB
     *
     * @param directorDTO DirectorDTO
     * @return ResponseMessageDTO
     */
    @PostMapping("/add")
    private ResponseEntity<ResponseMessageDTO> add(@RequestBody @Valid DirectorDTO directorDTO) {
        return directorService.add(directorDTO);
    }


    /**
     * This method is used for getting all the Director data in DB
     *
     * @return List of Director data
     */
    @GetMapping("/get")
    private ResponseEntity<List<Director>> getAll() {
        return directorService.getAll();
    }

    /**
     * This method is used for getting Director data by id in DB
     *
     * @param id Long
     * @return Director
     * If not exist return null
     */
    @GetMapping("/get/{id}")
    private ResponseEntity<Director> getById(@PathVariable Long id) {
        return directorService.getById(id);
    }


    /**
     * This method is used for edting Director data in DB
     *
     * @param id          Long
     * @param directorDTO DirectorDTO
     * @return ResponsDTO
     */
    @PutMapping("/edite/{id}")
    private ResponseEntity<ResponseMessageDTO> edite(@PathVariable Long id, @RequestBody @Valid DirectorDTO directorDTO) {
        return directorService.edite(id, directorDTO);
    }


    /**
     * This method is used for deleting Director data in DB
     *
     * @param id Long
     * @return ResponseMessage
     */
    private ResponseEntity<ResponseMessageDTO> delete(Long id) {
        return directorService.delete(id);
    }

}
