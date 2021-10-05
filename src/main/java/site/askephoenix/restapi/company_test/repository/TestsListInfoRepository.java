package site.askephoenix.restapi.company_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.company_test.model.TestsListInfo;

import java.util.List;

public interface TestsListInfoRepository extends JpaRepository<TestsListInfo, Long> {
    List<TestsListInfo> findAllByTests(CompanyTestsInfo info);
}
