package site.askephoenix.restapi.evaluation.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.evaluation.model.EvaluationInfo;
import site.askephoenix.restapi.evaluation.service.EvaluationService;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.service.UserService;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(EvaluationController.class)
class EvaluationControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    EvaluationService evaluationService;

    @MockBean
    UserService userService;

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
    @DisplayName("평가 지표 가져오기")
    @WithMockUser(username = "tester", authorities = "ROLE_USER")
    void readEvaluationOfBoard() throws Exception {
        BoardInfo testBoard = BoardInfo.builder()
                .seq(1L)
                .title("테스트 타이틀입니다.")
                .writer("테스트 유저")
                .category("회사 게시판")
                .companyName("테스트 회사")
                .content("내용")
                .isDeleted(false)
                .type("사용자")
                .createDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .build();
        EvaluationInfo testEvaluation = EvaluationInfo.builder()
                .id(1L)
                .gradations(1)
                .title("테스트 지표 1")
                .boardInfo(testBoard)
                .createDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .isDeleted(false)
                .build();
        HashMap<String, Object> data = Maps.newHashMap(ImmutableMap.of(
                "board", 1L,
                "evaluation", 1
        ));

        given(evaluationService.validate(any(UserInfo.class))).willReturn(1);
        given(evaluationService.message(anyInt())).willReturn(Maps.newHashMap(ImmutableMap.of("result","success")));
        given(evaluationService.load(anyInt(), anyLong())).willReturn(
                Maps.newHashMap(ImmutableMap.of(
                        "load",
                        testEvaluation,
                        "test",
                        "success")));

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.get("/api/boards/{board}/evaluations/{evaluation}",1L,1)
                        .with(csrf()).with(user(UserInfo.builder()
                                .auth("ROLE_USER")
                                .email("tester")
                                .build()
                        ))
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("evaluation-get",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("board").description("게시판 식별 번호"),
                                parameterWithName("evaluation").description("평가 기준표 식별 번호")
                        ),
                        responseFields(
                                fieldWithPath("load").type(JsonFieldType.OBJECT).description("정보 map"),
                                fieldWithPath("load.id").description("평가 기준표 식별자"),
                                fieldWithPath("load.boardInfo").description("평가 기준표를 작성한 게시판"),
                                fieldWithPath("load.boardInfo.seq").description("게시판 식별자"),
                                fieldWithPath("load.boardInfo.title").description("게시판 제목"),
                                fieldWithPath("load.boardInfo.writer").description("게시판 작성자"),
                                fieldWithPath("load.boardInfo.category").description("게시판 카테고리"),
                                fieldWithPath("load.boardInfo.companyName").description("게시판 작성 회사명"),
                                fieldWithPath("load.boardInfo.content").description("게시판 내용"),
                                fieldWithPath("load.boardInfo.deleted").description("게시판 삭제 여부(true 시, 삭제된것)"),
                                fieldWithPath("load.boardInfo.type").description("게시판 종류(일반, 회사면접)"),
                                fieldWithPath("load.boardInfo.createDate").description("게시판 생성 일시"),
                                fieldWithPath("load.boardInfo.updateDate").description("게시판 수정 일시"),
                                fieldWithPath("load.gradations").description("평가 기준표 순서"),
                                fieldWithPath("load.title").description("평가 기준표 제목"),
                                fieldWithPath("load.createDate").description("평가 기준표 생성 일시"),
                                fieldWithPath("load.updateDate").description("평가 기준표 가져온 정보"),
                                fieldWithPath("load.deleted").description("평가 기준표 삭제 여부"),
                                fieldWithPath("test").description("테스트 성공 여부")
                        )
                ))
        ;

    }
}