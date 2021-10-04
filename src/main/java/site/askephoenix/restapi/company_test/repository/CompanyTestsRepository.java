package site.askephoenix.restapi.company_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.List;

public interface CompanyTestsRepository extends JpaRepository<CompanyTestsInfo, Long> {

    List<CompanyTestsInfo> findAllByWriter(UserInfo writer);
}
