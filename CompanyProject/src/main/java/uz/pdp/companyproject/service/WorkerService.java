package uz.pdp.companyproject.service;

import net.bytebuddy.asm.MemberSubstitution;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.companyproject.entity.Address;
import uz.pdp.companyproject.entity.Department;
import uz.pdp.companyproject.entity.Worker;
import uz.pdp.companyproject.payload.ResponseMessageDTO;
import uz.pdp.companyproject.payload.WorkerDTO;
import uz.pdp.companyproject.repository.AddressRepository;
import uz.pdp.companyproject.repository.DepartmentRepository;
import uz.pdp.companyproject.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final AddressRepository addressRepository;

    private final DepartmentRepository departmentRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository, AddressRepository addressRepository, DepartmentRepository departmentRepository) {
        this.workerRepository = workerRepository;
        this.addressRepository = addressRepository;
        this.departmentRepository = departmentRepository;
    }


    /**
     * This method is used for adding department in DB
     *
     * @param workerDTO WorkerDTO
     * @return ResponseMessageDTO
     */
    public ResponseEntity<ResponseMessageDTO> add(WorkerDTO workerDTO) {

        Optional<Department> optional = departmentRepository.findById(workerDTO.getDepartMentId());
        if (optional.isEmpty()) return ResponseEntity.status(404).body(
                new ResponseMessageDTO("Please add department", false));

        Department department = optional.get();

        Address address = new Address();
        address.setCity(workerDTO.getCity());
        address.setStreet(workerDTO.getStreet());
        address.setHomeNumber(workerDTO.getHomeNumber());

        Address savedAddress = addressRepository.save(address);

        Worker worker = new Worker();
        worker.setFirstname(workerDTO.getFirstname());
        worker.setLastname(workerDTO.getLastname());
        worker.setPhone(workerDTO.getPhone());
        worker.setAddress(savedAddress);
        worker.setDepartMent(department);

        workerRepository.save(worker);

        return ResponseEntity.status(200).body(new ResponseMessageDTO("Successfully saved", true));
    }


    /**
     * This method is used for getting all the department data in db
     *
     * @return List of Deparment data
     */
    public ResponseEntity<List<Worker>> getAll() {
        return ResponseEntity.status(200).body(workerRepository.findAll());
    }

    /**
     * This method is used for getting Department data by id in DB
     *
     * @param id Long
     * @return Department
     * <p>
     * if this data is not exist return null
     */
    public ResponseEntity<Worker> getById(Long id) {
        Optional<Worker> optional = workerRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(404).body(null);
        return ResponseEntity.status(200).body(optional.get());
    }


    /**
     * This method is used for editing Worker data
     *
     * @param id        Long
     * @param workerDTO WorkerDTO
     * @return ResponseMessageDTO
     */
    public ResponseEntity<ResponseMessageDTO> edite(Long id, WorkerDTO workerDTO) {

        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()) return ResponseEntity.status(404).body(
                new ResponseMessageDTO("Worker not founded", false));

        boolean exists = workerRepository.existsByPhoneNot(workerDTO.getPhone());
        if (exists) return ResponseEntity.status(
                Response.SC_METHOD_NOT_ALLOWED).body(
                new ResponseMessageDTO("You cannot this phone cause it is exist", false));

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDTO.getDepartMentId());
        if (optionalDepartment.isEmpty()) return ResponseEntity.status(404).body
                (new ResponseMessageDTO("Not founded", false));


        Address address = new Address();
        address.setCity(workerDTO.getCity());
        address.setStreet(workerDTO.getStreet());
        address.setHomeNumber(workerDTO.getHomeNumber());
        Address savedAddress = addressRepository.save(address);
        Worker worker = optionalWorker.get();
        worker.setFirstname(workerDTO.getFirstname());
        worker.setLastname(workerDTO.getLastname());


        worker.setPhone(workerDTO.getPhone());
        worker.setAddress(savedAddress);
        worker.setDepartMent(optionalDepartment.get());

        workerRepository.save(worker);

        return ResponseEntity.status(200).body(new ResponseMessageDTO("Successfully edited", true));
    }


    /**
     * This method is used for deleting Worker data in DB
     *
     * @param id Long
     * @return ResponseMessageDTO
     */
    public ResponseEntity<ResponseMessageDTO> delete(Long id) {
        Optional<Worker> optional = workerRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(404).body(
                new ResponseMessageDTO("Not founded", false));
        workerRepository.delete(optional.get());

        return ResponseEntity.status(200).body(new ResponseMessageDTO("Successfully deleted", true));
    }
}
