package site.askephoenix.restapi.company_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;

public interface CompanyTestsRepository extends JpaRepository<CompanyTestsInfo, Long> {

}
