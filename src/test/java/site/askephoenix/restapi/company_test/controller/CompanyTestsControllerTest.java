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
                .type("").name("?????????").build();
        this.userDto = new UserResultDto(userInfo);

        this.userInfo2 = UserInfo.builder()
                .id(123L).auth("ROLE_USER").email("tester")
                .password(new BCryptPasswordEncoder().encode("1234test"))
                .type("").name("?????????").build();
        this.userInfo3 = UserInfo.builder()
                .id(456L).auth("ROLE_USER").email("tester")
                .password(new BCryptPasswordEncoder().encode("1234test"))
                .type("").name("?????????").build();
    }


    @Test
    @DisplayName(value = "????????? ?????? ?????? ?????? ?????? ????????????")
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
                                fieldWithPath("[]").description("???????????? ??????"),
                                fieldWithPath("[].id").description("???????????? ????????????"),
                                fieldWithPath("[].writer").description("???????????? ?????????"),
                                fieldWithPath("[].writer.id").description("????????? ????????????"),
                                fieldWithPath("[].writer.name").description("????????? ??????"),
                                fieldWithPath("[].writer.email").description("????????? ?????????"),
                                fieldWithPath("[].writer.type").description("????????? ??????(??????/??????)"),
                                fieldWithPath("[].testsListDto").description("????????? ???????????? (??????)"),
                                fieldWithPath("[].createDate[]").description("?????? ???/???/??? ???/???/??? ??????"),
                                fieldWithPath("[].createDate[0]").description("???"),
                                fieldWithPath("[].createDate[1]").description("???"),
                                fieldWithPath("[].createDate[2]").description("???"),
                                fieldWithPath("[].createDate[3]").description("???"),
                                fieldWithPath("[].createDate[4]").description("???"),
                                fieldWithPath("[].createDate[5]").description("???"),
                                fieldWithPath("[].createDate[6]").description("?????? ??? ???"),
                                fieldWithPath("[].updateDate[]").description("????????? ???/???/??? ???/???/??? ??????"),
                                fieldWithPath("[].updateDate[0]").description("???"),
                                fieldWithPath("[].updateDate[1]").description("???"),
                                fieldWithPath("[].updateDate[2]").description("???"),
                                fieldWithPath("[].updateDate[3]").description("???"),
                                fieldWithPath("[].updateDate[4]").description("???"),
                                fieldWithPath("[].updateDate[5]").description("???"),
                                fieldWithPath("[].updateDate[6]").description("?????? ??? ???")
                        )
                ));
    }

    @Test
    @DisplayName(value = "????????? ???????????? ????????? ???????????? ????????? ????????????")
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
                                fieldWithPath("[]").description("???????????? ??????"),
                                fieldWithPath("[].id").description("???????????? ????????????"),
                                fieldWithPath("[].writer").description("???????????? ????????? ??????"),
                                fieldWithPath("[].writer.id").description("????????? ????????????"),
                                fieldWithPath("[].writer.name").description("????????? ??????"),
                                fieldWithPath("[].writer.email").description("????????? ?????????"),
                                fieldWithPath("[].writer.type").description("????????? ??????(??????/??????)"),
                                fieldWithPath("[].testsListDto").description("????????? ???????????? (??????)"),
                                fieldWithPath("[].createDate[]").description("?????? ???/???/??? ???/???/??? ??????"),
                                fieldWithPath("[].createDate[0]").description("???"),
                                fieldWithPath("[].createDate[1]").description("???"),
                                fieldWithPath("[].createDate[2]").description("???"),
                                fieldWithPath("[].createDate[3]").description("???"),
                                fieldWithPath("[].createDate[4]").description("???"),
                                fieldWithPath("[].createDate[5]").description("???"),
                                fieldWithPath("[].createDate[6]").description("?????? ??? ???"),
                                fieldWithPath("[].updateDate[]").description("????????? ???/???/??? ???/???/??? ??????"),
                                fieldWithPath("[].updateDate[0]").description("???"),
                                fieldWithPath("[].updateDate[1]").description("???"),
                                fieldWithPath("[].updateDate[2]").description("???"),
                                fieldWithPath("[].updateDate[3]").description("???"),
                                fieldWithPath("[].updateDate[4]").description("???"),
                                fieldWithPath("[].updateDate[5]").description("???"),
                                fieldWithPath("[].updateDate[6]").description("?????? ??? ???")
                        )
                ));
    }

    @Test
    @DisplayName(value = "????????? ???????????? ????????? ???????????? ?????? ???????????? (??????)")
    void getDoTests() throws Exception {
        TestsListInfo listInfo = TestsListInfo
                .builder()
                .id(3L)
                .title("1?????? 100?????? ?????????")
                .answer("5050")
                .build();
        ResultDto dto1 = new ResultDto(
                CompanyTestsResultInfo.builder()
                        .id(2L)
                        .title("1?????? 100?????? ?????????")
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
                        .title("1?????? 101?????? ?????????")
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
                                fieldWithPath("[]").description("???????????? ?????? ??????"),
                                fieldWithPath("[].id").description("???????????? ?????? ????????????"),
                                fieldWithPath("[].testsListDto").description("????????? ?????? (??????)"),
                                fieldWithPath("[].tester").description("???????????? ????????? (??????)"),
                                fieldWithPath("[].sort_num").description("???????????? ?????? ????????????"),
                                fieldWithPath("[].title").description("?????? ????????? ??? ??????"),
                                fieldWithPath("[].answer").description("?????? ????????? ??? ??????")
                        )
                ));
    }

    @Test
    @DisplayName(value = "???????????? ?????? ????????????")
    void getTestList() throws Exception {
        CompanyTestsInfo testsInfo = CompanyTestsInfo.builder()
                .id(8L)
                .build();
        TestsListDto dto1 = new TestsListDto(
                TestsListInfo.builder()
                        .id(1L)
                        .title("?????? ?????? 1")
                        .contents("1?????? 100?????? ?????? ?????? ????????? ?????????????")
                        .answer("5050")
                        .tests(testsInfo)
                        .createDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build()
        );
        TestsListDto dto2 = new TestsListDto(
                TestsListInfo.builder()
                        .id(99999L)
                        .title("?????? ?????? 2")
                        .contents("1?????? 101?????? ?????? ?????? ????????? ?????????????")
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
                                parameterWithName("test").description("????????? ??????????????? ????????????")
                        ),
                        responseFields(
                                fieldWithPath("[]").description("???????????? ??????(?????? ??????) ??????"),
                                fieldWithPath("[].id").description("?????? ????????????"),
                                fieldWithPath("[].title").description("?????? ??????"),
                                fieldWithPath("[].contents").description("?????? ??????"),
                                fieldWithPath("[].answer").description("?????? ??????"),
                                fieldWithPath("[].tests_id").description("????????? ?????? ?????? (??????)"),
                                fieldWithPath("[].createDate[]").description("?????? ???/???/??? ???/???/??? ??????"),
                                fieldWithPath("[].createDate[0]").description("???"),
                                fieldWithPath("[].createDate[1]").description("???"),
                                fieldWithPath("[].createDate[2]").description("???"),
                                fieldWithPath("[].createDate[3]").description("???"),
                                fieldWithPath("[].createDate[4]").description("???"),
                                fieldWithPath("[].createDate[5]").description("???"),
                                fieldWithPath("[].createDate[6]").description("?????? ??? ???"),
                                fieldWithPath("[].updateDate[]").description("????????? ???/???/??? ???/???/??? ??????"),
                                fieldWithPath("[].updateDate[0]").description("???"),
                                fieldWithPath("[].updateDate[1]").description("???"),
                                fieldWithPath("[].updateDate[2]").description("???"),
                                fieldWithPath("[].updateDate[3]").description("???"),
                                fieldWithPath("[].updateDate[4]").description("???"),
                                fieldWithPath("[].updateDate[5]").description("???"),
                                fieldWithPath("[].updateDate[6]").description("?????? ??? ???")
                        )
                ));
    }

    @Test
    @DisplayName(value = "???????????? ???????????? (??????)")
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
                                fieldWithPath("id").description("????????? ???????????? ????????????"),
                                fieldWithPath("state").description("?????? ??????")
                        )
                ));
    }

    @Test
    @DisplayName(value = "???????????? ?????? ???????????? (??????)")
    void postTestList() throws Exception {
        TestsListDto listDto = new TestsListDto(TestsListInfo.builder()
                .id(1L)
                .title("?????? ?????? 1")
                .contents("1?????? 100?????? ?????? ?????? ????????? ?????????????")
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
                                parameterWithName("test").description("???????????? ?????? ??????")
                        ),
                        requestParameters(
                                parameterWithName("_csrf").description("?????? ?????????")
                        ),
                        requestFields(
                                fieldWithPath("title").description("?????? ??????"),
                                fieldWithPath("contents").description("?????? ??????"),
                                fieldWithPath("answer").description("?????? ??????"),
                                fieldWithPath("id").description("(??????)"),
                                fieldWithPath("tests_id").description("(??????)"),
                                fieldWithPath("createDate").description("(??????)"),
                                fieldWithPath("updateDate").description("(??????)")
                        ),
                        responseFields(
                                fieldWithPath("id").description("???????????? ?????? ????????????"),
                                fieldWithPath("state").description("?????? ??????")
                        )
                ));
    }

    @Test
    @DisplayName(value = "???????????? ???????????? (??????)")
    void postResultTests() throws Exception {
        ResultDto listDto1 = new ResultDto(CompanyTestsResultInfo.builder()
                .id(1L)
                .sort_num(1)
                .title("?????? ?????? 1")
                .answer("5050")
                .build()
        );
        ResultDto listDto2 = new ResultDto(CompanyTestsResultInfo.builder()
                .id(2L)
                .sort_num(2)
                .title("?????? ?????? 2")
                .answer("1313")
                .build()
        );
        ResultDto listDto3 = new ResultDto(CompanyTestsResultInfo.builder()
                .id(5L)
                .sort_num(3)
                .title("?????? ?????? 3")
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
                                parameterWithName("test").description("???????????? ????????????")
                        ),
                        requestParameters(
                                parameterWithName("_csrf").description("?????? ?????????")
                        ),
                        requestFields(
                                fieldWithPath("[]").description("?????? ???????????? ?????? ?????????"),
                                fieldWithPath("[]id").description("?????? ??????"),
                                fieldWithPath("[]sort_num").description("??????"),
                                fieldWithPath("[]title").description("?????? ??????"),
                                fieldWithPath("[]answer").description("?????? ?????????"),
                                fieldWithPath("[]testsListDto").description("(??????)"),
                                fieldWithPath("[]tester").description("(??????)")
                        ),
                        responseFields(
                                fieldWithPath("id_list[]").description("????????? ?????? ???????????? ??????"),
                                fieldWithPath("state").description("?????? ??????: data")
                        )
                ));
    }

    @Test
    @DisplayName(value = "???????????? ?????? ???????????? (??????)")
    void putTestList() throws Exception {
        TestsListDto listDto = new TestsListDto(TestsListInfo.builder()
                .id(1234L)
                .title("?????? ?????? ????????? 2 (??????)")
                .contents("?????? ????????? ???????????? ?????? ????????? ?????????????")
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
                                parameterWithName("list").description("?????? ?????? ??????")
                        ),
                        requestParameters(
                                parameterWithName("_csrf").description("?????? ?????????")
                        ),
                        requestFields(
                                fieldWithPath("title").description("????????? ?????? ??????"),
                                fieldWithPath("contents").description("????????? ?????? ??????"),
                                fieldWithPath("answer").description("????????? ?????? ??????"),
                                fieldWithPath("id").description("(??????)"),
                                fieldWithPath("tests_id").description("(??????)"),
                                fieldWithPath("createDate").description("(??????)"),
                                fieldWithPath("updateDate").description("(??????)")
                        ),
                        responseFields(
                                fieldWithPath("id").description("???????????? ?????? ????????????"),
                                fieldWithPath("state").description("?????? ??????")
                        )
                ));
    }

    @Test
    @DisplayName(value = "???????????? ?????? ?????? ???????????? (??????)")
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
                                parameterWithName("result").description("???????????? ?????? ????????????")
                        ),
                        requestParameters(
                                parameterWithName("_csrf").description("?????? ?????????")
                        ),
                        requestFields(
                                fieldWithPath("answer").description("????????? ?????? ?????????"),
                                fieldWithPath("id").description("(??????)"),
                                fieldWithPath("testsListDto").description("(??????)"),
                                fieldWithPath("tester").description("(??????)"),
                                fieldWithPath("sort_num").description("(??????)"),
                                fieldWithPath("title").description("(??????)")
                        ),
                        responseFields(
                                fieldWithPath("id").description("?????? ????????????"),
                                fieldWithPath("state").description("?????? ??????")
                        )
                ));
    }

    @Test
    @DisplayName(value = "???????????? ?????? ?????? (??????)")
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
                                parameterWithName("list").description("????????? ?????? ????????????")
                        ),
                        requestParameters(
                                parameterWithName("_csrf").description("?????? ?????????")
                        ),
                        responseFields(
                                fieldWithPath("id").description("????????? ???????????? ?????? ????????????"),
                                fieldWithPath("state").description("?????? ??????")
                        )
                ));
    }

    @Test
    @DisplayName(value = "???????????? ?????? ?????? ?????? (??????)")
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
                                parameterWithName("result").description("????????? ????????????")
                        ),
                        requestParameters(
                                parameterWithName("_csrf").description("?????? ?????????")
                        ),
                        responseFields(
                                fieldWithPath("id").description("????????? ????????????"),
                                fieldWithPath("state").description("?????? ??????")
                        )
                ));
    }
}