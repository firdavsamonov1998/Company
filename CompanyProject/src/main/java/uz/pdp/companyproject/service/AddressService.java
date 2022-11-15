package uz.pdp.companyproject.service;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.companyproject.entity.Address;
import uz.pdp.companyproject.payload.AddressDTO;
import uz.pdp.companyproject.payload.ResponseMessageDTO;
import uz.pdp.companyproject.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * This method is used for adding Address data in DB
     *
     * @param addressDTO AddressDTO
     * @return ResponseMessage
     */
    public ResponseEntity<ResponseMessageDTO> add(AddressDTO addressDTO) {

        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        address.setHomeNumber(addressDTO.getHomeNumber());
        addressRepository.save(address);
        return ResponseEntity.status(200).body(new ResponseMessageDTO("Successfully saved", true));
    }

    /**
     * This method is used for getting all the address data
     *
     * @return List of Address
     * If not exist return empty List
     */
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.status(200).body(addressRepository.findAll());
    }

    /**
     * This method is uded for getting Address data by id
     *
     * @param id Long
     * @return Address
     * if this address data is not exist return null
     */
    public ResponseEntity<Address> getById(Long id) {
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(Response.SC_NOT_FOUND).body(null);
        return ResponseEntity.status(200).body(optional.get());
    }


    /**
     * This method is used for editing the address data in DB
     *
     * @param id         Long
     * @param addressDTO AddressDTO
     * @return ResponsMessage
     */
    public ResponseEntity<ResponseMessageDTO> edite(Long id, AddressDTO addressDTO) {
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(404).body(
                new ResponseMessageDTO("This address is not exist", false));

        Address address = optional.get();
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        address.setHomeNumber(addressDTO.getHomeNumber());
        addressRepository.save(address);

        return ResponseEntity.status(200).body(new ResponseMessageDTO("Successfully saved", true));
    }


    /**
     * This method is used for deleting address data in DB
     *
     * @param id Long
     * @return ResponsMessage
     */
    public ResponseEntity<ResponseMessageDTO> delete(Long id) {

        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(404).body(
                new ResponseMessageDTO("This address data not founded", false));
        addressRepository.delete(optional.get());
        return ResponseEntity.status(200).body(new ResponseMessageDTO("Succussfully deleted", true));
    }
}