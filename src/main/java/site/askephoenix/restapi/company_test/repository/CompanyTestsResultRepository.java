package site.askephoenix.restapi.company_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.company_test.model.CompanyTestsResultInfo;

public interface CompanyTestsResultRepository extends JpaRepository<CompanyTestsResultInfo, Long> {

}
