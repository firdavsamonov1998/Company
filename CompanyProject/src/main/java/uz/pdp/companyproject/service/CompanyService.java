package uz.pdp.companyproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.companyproject.entity.Address;
import uz.pdp.companyproject.entity.Company;
import uz.pdp.companyproject.entity.Director;
import uz.pdp.companyproject.payload.CompanyDTO;
import uz.pdp.companyproject.payload.ResponseMessageDTO;
import uz.pdp.companyproject.repository.AddressRepository;
import uz.pdp.companyproject.repository.CompanyRepository;
import uz.pdp.companyproject.repository.DirectorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;

    private final DirectorRepository directorRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, AddressRepository addressRepository, DirectorRepository directorRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
        this.directorRepository = directorRepository;
    }

    /**
     * This method is used for adding new company data in DB
     *
     * @param companyDTO CompanyDto
     */

    public ResponseEntity<ResponseMessageDTO> add(CompanyDTO companyDTO) {

        Address address = new Address();
        address.setCity(companyDTO.getCity());
        address.setStreet(companyDTO.getStreet());
        address.setHomeNumber(companyDTO.getHomeNumber());

        Address savedAddress = addressRepository.save(address);

        Director director = new Director();
        director.setFirstname(companyDTO.getFirstname());
        director.setLastname(companyDTO.getLastname());

        Director savedDirector = directorRepository.save(director);
        Company company = new Company();
        company.setCompanyName(companyDTO.getCompanyName());
        company.setAddress(savedAddress);
        company.setDirector(savedDirector);
        companyRepository.save(company);

        return ResponseEntity.status(200).body(new ResponseMessageDTO("Successfully saved", true));
    }

    /**
     * This method is used for getting all the company data in DB
     *
     * @return List of company data
     */
    public ResponseEntity<List<Company>> getAll() {
        return ResponseEntity.status(200).body(companyRepository.findAll());
    }


    /**
     * This method is used for getting the company data in DB
     *
     * @param id Long
     * @return Company data
     * <p>
     * if company data is not exist in DB return null
     */
    public ResponseEntity<Company> getById(Long id) {

        Optional<Company> optional = companyRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(404).body(null);
        return ResponseEntity.status(200).body(optional.get());
    }


    /**
     * This method is used for editing the company data in DB
     *
     * @param id         Long
     * @param companyDTO CompanyDTO
     * @return ResponseMessageDTO
     */
    public ResponseEntity<ResponseMessageDTO> edite(Long id, CompanyDTO companyDTO) {
        Optional<Company> optional = companyRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(404).body(
                new ResponseMessageDTO("This company not founded", false));


        Company company = optional.get();
        Director director = company.getDirector();
        Address address = company.getAddress();

        address.setCity(companyDTO.getCity());
        address.setStreet(companyDTO.getStreet());
        address.setHomeNumber(companyDTO.getHomeNumber());
        Address editedAddress = addressRepository.save(address);

        director.setFirstname(companyDTO.getFirstname());
        director.setLastname(companyDTO.getLastname());
        Director editedDirector = directorRepository.save(director);

        company.setCompanyName(company.getCompanyName());
        company.setDirector(editedDirector);
        company.setAddress(editedAddress);

        return ResponseEntity.status(200).body(new ResponseMessageDTO("Successfully edited", true));
    }


    /**
     * This method is used for deleting company data in DB
     *
     * @param id Long
     * @return ResponseMessageDTO
     */
    public ResponseEntity<ResponseMessageDTO> delete(Long id) {

        Optional<Company> optional = companyRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.status(404).body(
                new ResponseMessageDTO("This company data not fouded", false));

        Company company = optional.get();
        companyRepository.delete(company);
        return ResponseEntity.status(200).body(new ResponseMessageDTO("Successfully deleted", true));
    }

}
