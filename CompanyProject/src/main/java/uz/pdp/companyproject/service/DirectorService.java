package uz.pdp.companyproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.companyproject.entity.Address;
import uz.pdp.companyproject.entity.Director;
import uz.pdp.companyproject.payload.DirectorDTO;
import uz.pdp.companyproject.payload.ResponseMessageDTO;
import uz.pdp.companyproject.repository.AddressRepository;
import uz.pdp.companyproject.repository.DirectorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public DirectorService(DirectorRepository directorRepository, AddressRepository addressRepository) {
        this.directorRepository = directorRepository;
        this.addressRepository = addressRepository;
    }


    /**
     * This method is used for adding Director data in DB
     *
     * @param directorDTO DirectorDTO
     * @return ResponseMessageDTO
     */
    public ResponseEntity<ResponseMessageDTO> add(DirectorDTO directorDTO) {

        Address address = new Address();
        address.setCity(directorDTO.getCity());
        address.setStreet(directorDTO.getStreet());
        address.setHomeNumber(directorDTO.getHomeNumber());
        Address savedAddress = addressRepository.save(address);


        Director director = new Director();
        director.setFirstname(directorDTO.getFirstname());
        director.setLastname(directorDTO.getLastname());
        director.setAddress(savedAddress);

        directorRepository.save(director);

        return ResponseEntity.status(200).body(new ResponseMessageDTO("Successfully saved", true));
    }


    /**
     * This method is used for getting all the Director data in DB
     *
     * @return List of Director data
     */
    public ResponseEntity<List<Director>> getAll() {
        return ResponseEntity.status(200).body(directorRepository.findAll());
    }


    /**
     * This method is used for getting Director data by id in DB
     *
     * @param id Long
     * @return Director
     * If not exist return null
     */
    public ResponseEntity<Director> getById(Long id) {
        Optional<Director> optional = directorRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(404).body(null);
        return ResponseEntity.status(200).body(optional.get());
    }


    /**
     * This method is used for edting Director data in DB
     *
     * @param id          Long
     * @param directorDTO DirectorDTO
     * @return ResponsDTO
     */
    public ResponseEntity<ResponseMessageDTO> edite(Long id, DirectorDTO directorDTO) {
        Optional<Director> optional = directorRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(404).body(new ResponseMessageDTO(
                "Not founded", false
        ));

        Director director = optional.get();
        Address address = director.getAddress();
        director.setFirstname(directorDTO.getFirstname());
        director.setLastname(directorDTO.getLastname());
        director.setAddress(address);

        directorRepository.save(director);

        return ResponseEntity.status(200).body(new ResponseMessageDTO("Successfully edited", true));
    }

    /**
     * This method is used for deleting Director data in DB
     *
     * @param id Long
     * @return ResponseMessage
     */

    public ResponseEntity<ResponseMessageDTO> delete(Long id) {
        Optional<Director> optional = directorRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(404).body(new ResponseMessageDTO(
                "Not founded", false
        ));

        Director director = optional.get();

        directorRepository.delete(director);

        return ResponseEntity.status(200).body(new ResponseMessageDTO(
                "Successfully deleted", true
        ));
    }

}
