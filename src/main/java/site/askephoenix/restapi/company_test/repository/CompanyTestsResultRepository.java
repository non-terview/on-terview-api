package site.askephoenix.restapi.company_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.askephoenix.restapi.company_test.model.CompanyTestsResultInfo;
import site.askephoenix.restapi.company_test.model.TestsListInfo;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.List;
import java.util.Optional;

public interface CompanyTestsResultRepository extends JpaRepository<CompanyTestsResultInfo, Long> {
    Optional<List<CompanyTestsResultInfo>> findAllByTester(UserInfo tester);

    @Query("select result from CompanyTestsResultInfo result where result.tester = ?1 and result.isDeleted = false")
    Optional<List<CompanyTestsResultInfo>> AllByTester(UserInfo tester);

    Optional<CompanyTestsResultInfo> findByList(TestsListInfo testsInfo);

    @Query("select result from CompanyTestsResultInfo result where result.id = ?1 and result.isDeleted = false")
    Optional<CompanyTestsResultInfo> searchById(Long test);
}
