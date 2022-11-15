package uz.pdp.companyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.companyproject.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    boolean existsByPhoneNot(String phone);
}
