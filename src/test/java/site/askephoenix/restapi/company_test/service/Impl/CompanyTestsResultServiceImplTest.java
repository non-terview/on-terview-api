package site.askephoenix.restapi.company_test.service.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.company_test.repository.CompanyTestsRepository;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CompanyTestsResultServiceImplTest {

    @Autowired
    private CompanyTestsRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser(){
        userRepository.save(UserInfo.builder()
                        .auth("USER_ROLE")
                        .type("USER")
                        .name("aske")
                        .email("aske_undaia@naver.com")
                        .password("asdf")
                .build())
                ;
    }

    @Test
    void readResultByTester() {
        repository.save(
                CompanyTestsInfo.builder()
                        .writer(UserInfo.builder()
                                .id(1L)
                                .type("")
                                .build()
                        )
                        .isDeleted(false)
                        .build()
        );
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}