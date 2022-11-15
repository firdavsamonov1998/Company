package uz.pdp.companyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.companyproject.entity.Director;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
