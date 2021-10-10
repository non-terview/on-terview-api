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
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import site.askephoenix.restapi.company_test.dto.CompanyTestsDto;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.company_test.service.CompanyTestsResultService;
import site.askephoenix.restapi.company_test.service.CompanyTestsService;
import site.askephoenix.restapi.company_test.service.TestsListService;
import site.askephoenix.restapi.user.dto.UserResultDto;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @MockBean
    UserService userService;

    @Mock
    UserInfo userInfo;

    @Mock
    UserResultDto userDto;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext,
               RestDocumentationContextProvider restDocumentation) {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .apply(documentationConfiguration(restDocumentation))
                .build();
        this.userInfo = UserInfo.builder()
                .id(1L).auth("ROLE_USER").email("tester")
                .password(new BCryptPasswordEncoder().encode("1234test"))
                .type("").name("김주윤").build();
        this.userDto = new UserResultDto(userInfo);
    }


    @Test
    @DisplayName(value = "등록된 모든 모의 시험 정보 가져오기")
    void getAllTests() throws Exception {
        UserInfo userInfo2 = UserInfo.builder()
                .id(123L).auth("ROLE_USER").email("tester")
                .password(new BCryptPasswordEncoder().encode("1234test"))
                .type("").name("정온유").build();
        UserInfo userInfo3 = UserInfo.builder()
                .id(456L).auth("ROLE_USER").email("tester")
                .password(new BCryptPasswordEncoder().encode("1234test"))
                .type("").name("박정현").build();
        CompanyTestsDto dto1 = new CompanyTestsDto(
                CompanyTestsInfo.builder()
                        .id(1L)
                        .writer(userInfo)
                        .createDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build()
        );
        CompanyTestsDto dto2 = new CompanyTestsDto(
                CompanyTestsInfo.builder()
                        .id(123L).writer(userInfo2).createDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build()
        );
        CompanyTestsDto dto3 = new CompanyTestsDto(
                CompanyTestsInfo.builder()
                        .id(234L).writer(userInfo3).createDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build()
        );
        List<CompanyTestsDto> dtoList = List.of(dto1, dto2, dto3);

        given(service.readAllTests()).willReturn(dtoList);


        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.get(
                                "/api/tests")
                        .with(csrf()).with(user(userInfo))
        );
        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("company_test-get",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                        ),
                        responseFields(
                                fieldWithPath("[]").description("모의시험 배열"),
                                fieldWithPath("[].id").description("모의시험 식별번호"),
                                fieldWithPath("[].writer").description("모의시험 작성자"),
                                fieldWithPath("[].writer.id").description("작성자 식별번호"),
                                fieldWithPath("[].writer.name").description("작성자 성명"),
                                fieldWithPath("[].testsListDto").description("테스트 항목배열 (빈값)"),
                                fieldWithPath("[].createDate[]").description("작성 년/월/일 시/분/초 총값"),
                                fieldWithPath("[].createDate[0]").description("년"),
                                fieldWithPath("[].createDate[1]").description("월"),
                                fieldWithPath("[].createDate[2]").description("일"),
                                fieldWithPath("[].createDate[3]").description("시"),
                                fieldWithPath("[].createDate[4]").description("분"),
                                fieldWithPath("[].createDate[5]").description("초"),
                                fieldWithPath("[].createDate[6]").description("시간 총 값"),
                                fieldWithPath("[].updateDate[]").description("작성 년/월/일 시/분/초 총값"),
                                fieldWithPath("[].updateDate[0]").description("년"),
                                fieldWithPath("[].updateDate[1]").description("월"),
                                fieldWithPath("[].updateDate[2]").description("일"),
                                fieldWithPath("[].updateDate[3]").description("시"),
                                fieldWithPath("[].updateDate[4]").description("분"),
                                fieldWithPath("[].updateDate[5]").description("초"),
                                fieldWithPath("[].updateDate[6]").description("시간 총 값")
                        )
                ));
    }

    @Test
    @DisplayName(value = "로그인 사용자가 작성한 모의시험 리스트 가져오기")
    void getCompanyTests() throws Exception {
        CompanyTestsDto dto1 = new CompanyTestsDto(
                CompanyTestsInfo.builder()
                        .id(1L)
                        .writer(userInfo)
                        .createDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build()
        );
        CompanyTestsDto dto2 = new CompanyTestsDto(
                CompanyTestsInfo.builder()
                        .id(2L)
                        .writer(userInfo)
                        .createDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build()
        );
        List<CompanyTestsDto> dtoList = List.of(dto1, dto2);

        given(service.readUserTests(any(UserInfo.class))).willReturn(dtoList);


        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.get(
                                "/api/tests/user")
                        .with(csrf()).with(user(userInfo))
        );
        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("company_test-get-user",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                        ),
                        responseFields(
                                fieldWithPath("[]").description("모의시험 배열"),
                                fieldWithPath("[].id").description("모의시험 식별번호"),
                                fieldWithPath("[].writer").description("모의시험 작성자"),
                                fieldWithPath("[].writer.id").description("작성자 식별번호"),
                                fieldWithPath("[].writer.name").description("작성자 성명"),
                                fieldWithPath("[].testsListDto").description("테스트 항목배열 (빈값)"),
                                fieldWithPath("[].createDate[]").description("작성 년/월/일 시/분/초 총값"),
                                fieldWithPath("[].createDate[0]").description("년"),
                                fieldWithPath("[].createDate[1]").description("월"),
                                fieldWithPath("[].createDate[2]").description("일"),
                                fieldWithPath("[].createDate[3]").description("시"),
                                fieldWithPath("[].createDate[4]").description("분"),
                                fieldWithPath("[].createDate[5]").description("초"),
                                fieldWithPath("[].createDate[6]").description("시간 총 값"),
                                fieldWithPath("[].updateDate[]").description("작성 년/월/일 시/분/초 총값"),
                                fieldWithPath("[].updateDate[0]").description("년"),
                                fieldWithPath("[].updateDate[1]").description("월"),
                                fieldWithPath("[].updateDate[2]").description("일"),
                                fieldWithPath("[].updateDate[3]").description("시"),
                                fieldWithPath("[].updateDate[4]").description("분"),
                                fieldWithPath("[].updateDate[5]").description("초"),
                                fieldWithPath("[].updateDate[6]").description("시간 총 값")
                        )
                ));
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