package uz.pdp.companyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.companyproject.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
