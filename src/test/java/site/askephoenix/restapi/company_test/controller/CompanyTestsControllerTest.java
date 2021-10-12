package site.askephoenix.restapi.company_test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import site.askephoenix.restapi.company_test.dto.CompanyTestsDto;
import site.askephoenix.restapi.company_test.dto.ResultDto;
import site.askephoenix.restapi.company_test.dto.TestsListDto;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.company_test.model.CompanyTestsResultInfo;
import site.askephoenix.restapi.company_test.model.TestsListInfo;
import site.askephoenix.restapi.company_test.service.CompanyTestsResultService;
import site.askephoenix.restapi.company_test.service.CompanyTestsService;
import site.askephoenix.restapi.company_test.service.TestsListService;
import site.askephoenix.restapi.user.dto.UserResultDto;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
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
    UserInfo userInfo2;
    @Mock
    UserInfo userInfo3;

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

        this.userInfo2 = UserInfo.builder()
                .id(123L).auth("ROLE_USER").email("tester")
                .password(new BCryptPasswordEncoder().encode("1234test"))
                .type("").name("정온유").build();
        this.userInfo3 = UserInfo.builder()
                .id(456L).auth("ROLE_USER").email("tester")
                .password(new BCryptPasswordEncoder().encode("1234test"))
                .type("").name("박정현").build();
    }


    @Test
    @DisplayName(value = "등록된 모든 모의 시험 정보 가져오기")
    void getAllTests() throws Exception {
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
                                fieldWithPath("[].writer.email").description("작성자 이메일"),
                                fieldWithPath("[].writer.type").description("작성자 구분(회사/개인)"),
                                fieldWithPath("[].testsListDto").description("테스트 항목배열 (빈값)"),
                                fieldWithPath("[].createDate[]").description("작성 년/월/일 시/분/초 총값"),
                                fieldWithPath("[].createDate[0]").description("년"),
                                fieldWithPath("[].createDate[1]").description("월"),
                                fieldWithPath("[].createDate[2]").description("일"),
                                fieldWithPath("[].createDate[3]").description("시"),
                                fieldWithPath("[].createDate[4]").description("분"),
                                fieldWithPath("[].createDate[5]").description("초"),
                                fieldWithPath("[].createDate[6]").description("시간 총 값"),
                                fieldWithPath("[].updateDate[]").description("수정한 년/월/일 시/분/초 총값"),
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
                                fieldWithPath("[].writer").description("모의시험 작성자 정보"),
                                fieldWithPath("[].writer.id").description("작성자 식별번호"),
                                fieldWithPath("[].writer.name").description("작성자 성명"),
                                fieldWithPath("[].writer.email").description("작성자 이메일"),
                                fieldWithPath("[].writer.type").description("작성자 구분(회사/개인)"),
                                fieldWithPath("[].testsListDto").description("테스트 항목배열 (빈값)"),
                                fieldWithPath("[].createDate[]").description("작성 년/월/일 시/분/초 총값"),
                                fieldWithPath("[].createDate[0]").description("년"),
                                fieldWithPath("[].createDate[1]").description("월"),
                                fieldWithPath("[].createDate[2]").description("일"),
                                fieldWithPath("[].createDate[3]").description("시"),
                                fieldWithPath("[].createDate[4]").description("분"),
                                fieldWithPath("[].createDate[5]").description("초"),
                                fieldWithPath("[].createDate[6]").description("시간 총 값"),
                                fieldWithPath("[].updateDate[]").description("수정한 년/월/일 시/분/초 총값"),
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
    void getDoTests() throws Exception {
        TestsListInfo listInfo = TestsListInfo
                .builder()
                .id(3L)
                .title("1부터 100까지 더하기")
                .answer("5050")
                .build();
        ResultDto dto1 = new ResultDto(
                CompanyTestsResultInfo.builder()
                        .id(2L)
                        .title("1부터 100까지 더하기")
                        .list(listInfo)
                        .tester(userInfo2)
                        .sort_num(1)
                        .answer("5050")
                        .createDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build()
        );
        ResultDto dto2 = new ResultDto(
                CompanyTestsResultInfo.builder()
                        .id(9L)
                        .title("1부터 101까지 더하기")
                        .list(listInfo)
                        .tester(userInfo2)
                        .sort_num(2)
                        .answer("5151")
                        .createDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build()
        );
        List<ResultDto> dtoList = List.of(dto1, dto2);

        given(resultService.readResultByTester(any(UserInfo.class))).willReturn(dtoList);


        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.get(
                                "/api/tests/results/user")
                        .with(csrf()).with(user(userInfo2))
        );
        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("company_test-get-results-user",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                        ),
                        responseFields(
                                fieldWithPath("[]").description("모의시험 결과 배열"),
                                fieldWithPath("[].id").description("모의시험 결과 식별번호"),
                                fieldWithPath("[].testsListDto").description("테스트 항목 (빈값)"),
                                fieldWithPath("[].tester").description("모의시험 응시자 (빈값)"),
                                fieldWithPath("[].sort_num").description("모의시험 응시 정렬순번"),
                                fieldWithPath("[].title").description("문제 풀었을 때 제목"),
                                fieldWithPath("[].answer").description("문제 풀었을 때 정답")
                        )
                ));
    }

    @Test
    @DisplayName(value = "모의시험 문항 가져오기")
    void getTestList() throws Exception {
        CompanyTestsInfo testsInfo = CompanyTestsInfo.builder()
                .id(8L)
                .build();
        TestsListDto dto1 = new TestsListDto(
                TestsListInfo.builder()
                        .id(1L)
                        .title("수학 기초 1")
                        .contents("1부터 100까지 모든 수를 더하면 몇인가요?")
                        .answer("5050")
                        .tests(testsInfo)
                        .createDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build()
        );
        TestsListDto dto2 = new TestsListDto(
                TestsListInfo.builder()
                        .id(99999L)
                        .title("수학 기초 2")
                        .contents("1부터 101까지 모든 수를 더하면 몇인가요?")
                        .answer("5151")
                        .tests(testsInfo)
                        .createDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build()
        );
        List<TestsListDto> dtoList = List.of(dto1, dto2);

        given(listService.readTestsList(anyLong())).willReturn(dtoList);


        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.get(
                                "/api/tests/{test}/list", 1L)
                        .with(csrf()).with(user(userInfo))
        );
        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("company_test-get-test-list",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("test").description("등록된 모의시험의 식별번호")
                        ),
                        responseFields(
                                fieldWithPath("[]").description("모의시험 문항(혹은 문제) 배열"),
                                fieldWithPath("[].id").description("문항 식별번호"),
                                fieldWithPath("[].title").description("문항 제목"),
                                fieldWithPath("[].contents").description("문항 내용"),
                                fieldWithPath("[].answer").description("문항 정답"),
                                fieldWithPath("[].tests_id").description("테스트 식별 번호 (빈값)"),
                                fieldWithPath("[].createDate[]").description("작성 년/월/일 시/분/초 총값"),
                                fieldWithPath("[].createDate[0]").description("년"),
                                fieldWithPath("[].createDate[1]").description("월"),
                                fieldWithPath("[].createDate[2]").description("일"),
                                fieldWithPath("[].createDate[3]").description("시"),
                                fieldWithPath("[].createDate[4]").description("분"),
                                fieldWithPath("[].createDate[5]").description("초"),
                                fieldWithPath("[].createDate[6]").description("시간 총 값"),
                                fieldWithPath("[].updateDate[]").description("수정한 년/월/일 시/분/초 총값"),
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
    @DisplayName(value = "모의시험 등록하기 (회사)")
    void postCompanyTests() throws Exception {
        given(service.save(any(UserInfo.class))).willReturn(5L);

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.post(
                                "/api/tests")
                        .with(csrf()).with(user(userInfo))
        );
        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("company_test-post-tests",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                        ),
                        responseFields(
                                fieldWithPath("id").description("등록된 모의시험 식별번호"),
                                fieldWithPath("state").description("성공 여부")
                        )
                ));
    }

    @Test
    @DisplayName(value = "모의시험 항목 추가하기 (회사)")
    void postTestList() throws Exception {
        TestsListDto listDto = new TestsListDto(TestsListInfo.builder()
                .id(1L)
                .title("수학 기초 1")
                .contents("1부터 100까지 모든 수를 더하면 몇인가요?")
                .answer("5050")
                .build()
        );

        given(listService.save(any(TestsListDto.class), any(UserInfo.class))).willReturn(5L);

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.post(
                                "/api/tests/{test}/list", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(listDto))
                        .characterEncoding("utf-8")
                        .with(csrf()).with(user(userInfo))
        );
        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("company_test-post-tests-lists",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("test").description("모의시험 식별 번호")
                        ),
                        requestParameters(
                                parameterWithName("_csrf").description("인증 데이터")
                        ),
                        requestFields(
                                fieldWithPath("title").description("문항 제목"),
                                fieldWithPath("contents").description("문항 질문"),
                                fieldWithPath("answer").description("문항 정답"),
                                fieldWithPath("id").description("(빈값)"),
                                fieldWithPath("tests_id").description("(빈값)"),
                                fieldWithPath("createDate").description("(빈값)"),
                                fieldWithPath("updateDate").description("(빈값)")
                        ),
                        responseFields(
                                fieldWithPath("id").description("모의시험 항목 식별번호"),
                                fieldWithPath("state").description("성공 여부")
                        )
                ));
    }

    @Test
    @DisplayName(value = "모의시험 응시하기 (개인)")
    void postResultTests() throws Exception {
        ResultDto listDto1 = new ResultDto(CompanyTestsResultInfo.builder()
                .id(1L)
                .sort_num(1)
                .title("수학 기초 1")
                .answer("5050")
                .build()
        );
        ResultDto listDto2 = new ResultDto(CompanyTestsResultInfo.builder()
                .id(2L)
                .sort_num(2)
                .title("수학 기초 2")
                .answer("1313")
                .build()
        );
        ResultDto listDto3 = new ResultDto(CompanyTestsResultInfo.builder()
                .id(5L)
                .sort_num(3)
                .title("수학 기초 3")
                .answer("501")
                .build()
        );
        List<ResultDto> resultDtoList = List.of(listDto1, listDto2, listDto3);

        List<Long> longs = List.of(1L, 2L, 5L);

        given(resultService.save(anyLong(), any(), any(UserInfo.class))).willReturn(longs);

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.post(
                                "/api/tests/{test}/results", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(resultDtoList))
                        .characterEncoding("utf-8")
                        .with(csrf()).with(user(userInfo))
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("company_test-post-tests-results",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("test").description("모의시험 식별번호")
                        ),
                        requestParameters(
                                parameterWithName("_csrf").description("인증 데이터")
                        ),
                        requestFields(
                                fieldWithPath("[]").description("개인 모의시험 결과 리스트"),
                                fieldWithPath("[]id").description("결과 번호"),
                                fieldWithPath("[]sort_num").description("순번"),
                                fieldWithPath("[]title").description("당시 제목"),
                                fieldWithPath("[]answer").description("결과 입력값"),
                                fieldWithPath("[]testsListDto").description("(빈값)"),
                                fieldWithPath("[]tester").description("(빈값)")
                        ),
                        responseFields(
                                fieldWithPath("id_list[]").description("저장된 결과 식별번호 배열"),
                                fieldWithPath("state").description("성공 여부: data")
                        )
                ));
    }

    @Test
    @DisplayName(value = "모의시험 항목 수정하기 (회사)")
    void putTestList() throws Exception {
        TestsListDto listDto = new TestsListDto(TestsListInfo.builder()
                .id(1234L)
                .title("수학 기초 넌센스 2 (수정)")
                .contents("모든 사람의 머리카락 수를 곱하면 몇일까요?")
                .answer("0")
                .build()
        );

        given(listService.update(any(TestsListDto.class), any(UserInfo.class))).willReturn(5L);

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.put(
                                "/api/tests/lists/{list}", 1234L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(listDto))
                        .characterEncoding("utf-8")
                        .with(csrf()).with(user(userInfo))
        );
        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("company_test-put-tests-lists",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("list").description("항목 식별 번호")
                        ),
                        requestParameters(
                                parameterWithName("_csrf").description("인증 데이터")
                        ),
                        requestFields(
                                fieldWithPath("title").description("수정할 문항 제목"),
                                fieldWithPath("contents").description("수정할 문항 질문"),
                                fieldWithPath("answer").description("수정할 문항 정답"),
                                fieldWithPath("id").description("(빈값)"),
                                fieldWithPath("tests_id").description("(빈값)"),
                                fieldWithPath("createDate").description("(빈값)"),
                                fieldWithPath("updateDate").description("(빈값)")
                        ),
                        responseFields(
                                fieldWithPath("id").description("모의시험 항목 식별번호"),
                                fieldWithPath("state").description("성공 여부")
                        )
                ));
    }

    @Test
    @DisplayName(value = "모의시험 문제 정답 수정하기 (개인)")
    void putResultTests() throws Exception {
        ResultDto listDto = new ResultDto(CompanyTestsResultInfo.builder()
                .answer("5051")
                .build()
        );

        given(resultService.update(anyLong(), any(), any(UserInfo.class))).willReturn(1L);

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.put(
                                "/api/tests/results/{result}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(listDto))
                        .characterEncoding("utf-8")
                        .with(csrf()).with(user(userInfo))
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("company_test-put-tests-results",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("result").description("응시결과 문제 식별번호")
                        ),
                        requestParameters(
                                parameterWithName("_csrf").description("인증 데이터")
                        ),
                        requestFields(
                                fieldWithPath("answer").description("수정할 결과 입력값"),
                                fieldWithPath("id").description("(빈값)"),
                                fieldWithPath("testsListDto").description("(빈값)"),
                                fieldWithPath("tester").description("(빈값)"),
                                fieldWithPath("sort_num").description("(빈값)"),
                                fieldWithPath("title").description("(빈값)")
                        ),
                        responseFields(
                                fieldWithPath("id").description("결과 식별번호"),
                                fieldWithPath("state").description("성공 여부")
                        )
                ));
    }

    @Test
    @DisplayName(value = "모의시험 항목 삭제 (회사)")
    void deleteTestList() throws Exception {
        given(listService.delete(anyLong(), any(UserInfo.class))).willReturn(1L);

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.delete(
                                "/api/tests/lists/{list}", 1L)
                        .characterEncoding("utf-8")
                        .with(csrf()).with(user(userInfo))
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("company_test-delete-tests-lists",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("list").description("선택한 항목 식별번호")
                        ),
                        requestParameters(
                                parameterWithName("_csrf").description("인증 데이터")
                        ),
                        responseFields(
                                fieldWithPath("id").description("삭제된 모의시험 항목 식별번호"),
                                fieldWithPath("state").description("성공 여부")
                        )
                ));
    }

    @Test
    @DisplayName(value = "모의시험 문제 정답 삭제 (개인)")
    void deleteResultTests() throws Exception {
        given(resultService.delete(anyLong(), any(UserInfo.class))).willReturn(1L);

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.delete(
                                "/api/tests/results/{result}", 1L)
                        .characterEncoding("utf-8")
                        .with(csrf()).with(user(userInfo))
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("company_test-delete-tests-results",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("result").description("선택한 응시결과")
                        ),
                        requestParameters(
                                parameterWithName("_csrf").description("인증 데이터")
                        ),
                        responseFields(
                                fieldWithPath("id").description("삭제된 응시결과"),
                                fieldWithPath("state").description("성공 여부")
                        )
                ));
    }
}