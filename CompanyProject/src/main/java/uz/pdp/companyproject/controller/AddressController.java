package uz.pdp.companyproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import uz.pdp.companyproject.entity.Address;
import uz.pdp.companyproject.payload.AddressDTO;
import uz.pdp.companyproject.payload.ResponseMessageDTO;
import uz.pdp.companyproject.service.AddressService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    /**
     * This method is used for adding Address data in DB
     *
     * @param addressDTO AddressDTO
     * @return ResponsMessage
     */
    @PostMapping("/add")
    private ResponseEntity<ResponseMessageDTO> add(@RequestBody @Valid AddressDTO addressDTO) {
        return addressService.add(addressDTO);
    }


    /**
     * This method is used for getting all the Address data in DB
     *
     * @return List of Address data in DB
     */
    @GetMapping("/getAll")
    private ResponseEntity<List<Address>> getAll() {
        return addressService.getAll();
    }


    /**
     * This method is uded for getting Address data by id
     *
     * @param id Long
     * @return Address
     * if this address data is not exist return null
     */
    @GetMapping("/get/{id}")
    private ResponseEntity<Address> getById(@PathVariable Long id) {
        return addressService.getById(id);
    }


    /**
     * This method is used for editing the address data in DB
     *
     * @param id         Long
     * @param addressDTO AddressDTO
     * @return ResponsMessage
     */
    @PutMapping("/edite/{id}")
    private ResponseEntity<ResponseMessageDTO> edite(@PathVariable Long id, @RequestBody @Valid AddressDTO addressDTO) {
        return addressService.edite(id, addressDTO);
    }


    /**
     * This method is used for deleting address data in DB
     *
     * @param id Long
     * @return ResponsMessage
     */
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ResponseMessageDTO> delete(@PathVariable Long id) {
        return addressService.delete(id);
    }

}
