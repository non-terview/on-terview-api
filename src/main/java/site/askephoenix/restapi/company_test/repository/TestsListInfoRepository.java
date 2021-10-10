package site.askephoenix.restapi.company_test.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.company_test.model.TestsListInfo;

import java.util.List;
import java.util.Optional;

public interface TestsListInfoRepository extends JpaRepository<TestsListInfo, Long> {
    @Query("select list from TestsListInfo list where list.tests = ?1 and list.isDeleted = false ")
    Optional<List<TestsListInfo>> searchAllByTests(CompanyTestsInfo tests);

    Optional<TestsListInfo> findByTests(CompanyTestsInfo info);
    @NonNull
    Optional<TestsListInfo> findById(@NonNull Long id);

}
