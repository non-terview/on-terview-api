package site.askephoenix.restapi.company_test.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import site.askephoenix.restapi.company_test.service.CompanyTestsResultService;
import site.askephoenix.restapi.company_test.service.CompanyTestsService;
import site.askephoenix.restapi.company_test.service.TestsListService;
import site.askephoenix.restapi.user.model.UserInfo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(CompanyTestsController.class)
class CompanyTestsControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    CompanyTestsService service;

    @MockBean
    CompanyTestsResultService resultService;

    @MockBean
    TestsListService listService;

    @Mock
    UserInfo userInfo;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext,
               RestDocumentationContextProvider restDocumentation) {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }


    @Test
    @DisplayName(value = "등록된 모든 모의 시험 정보 가져오기")
    void getAllTests() {

    }

    @Test
    @DisplayName(value = "로그인 사용자가 작성한 모의시험 리스트 가져오기")
    void getCompanyTests() {
        UserInfo userInfo = UserInfo.builder()
                .auth("ROLE_USER")
                .email("tester")
                .build();
        given(service.readUserTests(any(UserInfo.class))).willReturn(null);
    }

    @Test
    @DisplayName(value = "로그인 사용자가 응시한 모의시험 결과 가져오기 (개인)")
    void getDoTests() {
    }

    @Test
    @DisplayName(value = "모의시험 문항 가져오기")
    void getTestList() {
    }

    @Test
    @DisplayName(value = "모의시험 응시하기 (개인)")
    void postResultTests() {
    }

    @Test
    @DisplayName(value = "모의시험 등록하기 (회사)")
    void postCompanyTests() {
    }

    @Test
    @DisplayName(value = "모의시험 항목 추가하기 (회사)")
    void postTestList() {
    }

    @Test
    @DisplayName(value = "모의시험 항목 수정하기 (회사)")
    void putTestList() {
    }

    @Test
    @DisplayName(value = "모의시험 문제 정답 수정하기 (개인)")
    void putResultTests() {
    }

    @Test
    @DisplayName(value = "모의시험 항목 삭제 (회사)")
    void deleteTestList() {
    }

    @Test
    @DisplayName(value = "모의시험 문제 정답 삭제 (개인)")
    void deleteResultTests() {
    }
}