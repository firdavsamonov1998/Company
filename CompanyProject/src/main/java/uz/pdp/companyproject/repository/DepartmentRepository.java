package uz.pdp.companyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.companyproject.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
