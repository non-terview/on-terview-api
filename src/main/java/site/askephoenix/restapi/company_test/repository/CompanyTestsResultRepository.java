package site.askephoenix.restapi.company_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.company_test.model.CompanyTestsResultInfo;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.List;

public interface CompanyTestsResultRepository extends JpaRepository<CompanyTestsResultInfo, Long> {
    List<CompanyTestsResultInfo> findAllByTester(UserInfo tester);

}
