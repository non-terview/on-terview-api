package site.askephoenix.restapi.company_test.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.List;
import java.util.Optional;

public interface CompanyTestsRepository extends JpaRepository<CompanyTestsInfo, Long> {
    @NonNull
    Optional<CompanyTestsInfo> findById(@NonNull Long user);

    List<CompanyTestsInfo> findAllByWriter(UserInfo writer);

    @Query("select tests from CompanyTestsInfo tests where tests.isDeleted = false")
    List<CompanyTestsInfo> All();

}
