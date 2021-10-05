package site.askephoenix.restapi.company_test.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.List;
import java.util.Optional;

public interface CompanyTestsRepository extends JpaRepository<CompanyTestsInfo, Long> {
    @NonNull
    Optional<CompanyTestsInfo> findById(@NonNull Long user);
    List<CompanyTestsInfo> findAllByWriter(UserInfo writer);
}
