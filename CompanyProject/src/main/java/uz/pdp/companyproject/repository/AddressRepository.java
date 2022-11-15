package uz.pdp.companyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.companyproject.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
