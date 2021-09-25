package site.askephoenix.restapi.evaluation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.evaluation.dto.EvaluationDetailInfoDto;
import site.askephoenix.restapi.evaluation.dto.EvaluationInfoDto;
import site.askephoenix.restapi.evaluation.dto.EvaluationTypeListDto;
import site.askephoenix.restapi.evaluation.model.EvaluationInfo;
import site.askephoenix.restapi.evaluation.model.EvaluationTypeList;
import site.askephoenix.restapi.evaluation.service.EvaluationService;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.service.UserService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.*;
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
    @DisplayName("평가 지표 가져오기 Success")
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

        given(evaluationService.validate(any(UserInfo.class))).willReturn(1);
        given(evaluationService.message(anyInt())).willReturn(Maps.newHashMap(ImmutableMap.of("result", "success")));
        given(evaluationService.load(anyInt(), anyLong())).willReturn(
                Maps.newHashMap(ImmutableMap.of(
                        "load",
                        testEvaluation,
                        "test",
                        "success")));

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.get("/api/boards/{board}/evaluations/{evaluation}", 1L, 1)
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
                ));
    }

    @Test
    @DisplayName("평가 지표 생성하기 Success")
    void createEvaluation() throws Exception {
        BoardInfo boardInfo = BoardInfo.builder()
                .seq(1L)
                .companyName("(주)바다기업")
                .type("회사 게시판")
                .category("면접 모집")
                .writer("면접관 A")
                .title("마늘을 잘 먹는 직원을 구합니다.")
                .content("~~~~ 이런 내용으로 면접 모집 공지하는 내용입니다.")
                .isDeleted(false)
                .build();
        EvaluationInfo evaluationInfo = EvaluationInfo.builder()
                .id(3L)
                .boardInfo(boardInfo)
                .gradations(3)
                .title("인사직원 채점 지표")
                .isDeleted(false)
                .build();

        EvaluationInfoDto infoDto = new EvaluationInfoDto(evaluationInfo);
        HashMap<String, Object> map = Maps.newHashMap(ImmutableMap.of("infoDto", infoDto));


        given(evaluationService.validate(any(UserInfo.class))).willReturn(1);
        given(evaluationService.message(anyInt())).willReturn(Maps.newHashMap(ImmutableMap.of("result", "success")));
        given(evaluationService.save(anyInt(), anyLong(), any(EvaluationInfoDto.class))).willReturn(
                Maps.newHashMap(ImmutableMap.of(
                        "evaluation_key",
                        1,
                        "code",
                        "success")));

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.post("/api/boards/{board}/evaluations/{evaluation}", 1L, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(map))
                        .characterEncoding("utf-8")
                        .with(csrf()).with(user(UserInfo.builder()
                                .auth("ROLE_USER")
                                .email("tester")
                                .build()
                        ))
        );

        perform.andExpect(status().isOk())
                .andDo(print()
                )
                .andDo(document("evaluation-post",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint())
                                ,
                                pathParameters(
                                        parameterWithName("board").description("게시판 식별 번호"),
                                        parameterWithName("evaluation").description("평가 기준표 식별 번호")
                                ),
                                requestParameters(
                                        parameterWithName("_csrf").description("인증 데이터")
                                ),
                                requestFields(
                                        fieldWithPath("infoDto").description("평가지표 data"),
                                        fieldWithPath("infoDto.details[]").description("평가지표에 해당하는 지문 리스트"),
                                        fieldWithPath("infoDto.id").description("평가지표 - 식별 번호"),
                                        fieldWithPath("infoDto.boardDto").description("게시판 정보 data"),
                                        fieldWithPath("infoDto.boardDto.seq").description("게시판 정보 - 번호"),
                                        fieldWithPath("infoDto.boardDto.company").description("게시판 정보 - 회사명"),
                                        fieldWithPath("infoDto.boardDto.type").description("게시판 정보 - 게시판 권한 종류"),
                                        fieldWithPath("infoDto.boardDto.category").description("게시판 정보 - 게시판 종류"),
                                        fieldWithPath("infoDto.boardDto.writer").description("게시판 정보 - 작성자"),
                                        fieldWithPath("infoDto.boardDto.title").description("게시판 정보 - 제목"),
                                        fieldWithPath("infoDto.boardDto.content").description("게시판 정보 - 내용"),
                                        fieldWithPath("infoDto.boardDto.deleted").description("게시판 정보 - 삭제 여부"),

                                        fieldWithPath("infoDto.gradations").description("평가지표 - 정렬순서"),
                                        fieldWithPath("infoDto.title").description("평가지표 - 제목"),
                                        fieldWithPath("infoDto.deleted").description("평가지표 - 삭제 여부")

                                ),
                                responseFields(
                                        fieldWithPath("evaluation_key").description("생성된 평가지표 식별번호"),
                                        fieldWithPath("code").description("성공 여부")
                                )
                        )
                );
    }

    @Test
    @DisplayName("평가 지표 지문의 타입 가져오기 Success")
    void readEvaluationType() throws Exception {

        EvaluationTypeList typeList1 = EvaluationTypeList.builder()
                .id(3000L)
                .name("매우 좋음")
                .gradations(1)
                .build();
        EvaluationTypeList typeList2 = EvaluationTypeList.builder()
                .id(3002L)
                .name("좋음")
                .gradations(2)
                .build();
        EvaluationTypeList typeList3 = EvaluationTypeList.builder()
                .id(3006L)
                .name("보통")
                .gradations(3)
                .build();
        EvaluationTypeList typeList4 = EvaluationTypeList.builder()
                .id(2001L)
                .name("나쁨")
                .gradations(4)
                .build();
        EvaluationTypeList typeList5 = EvaluationTypeList.builder()
                .id(300L)
                .name("매우 나쁨")
                .gradations(5)
                .build();
        List<EvaluationTypeList> evaluationTypeLists =
                Lists.newArrayList(ImmutableList.of(
                        typeList1, typeList2,
                        typeList3, typeList4,
                        typeList5
                ));
        List<EvaluationTypeListDto> result = evaluationTypeLists.stream().map(EvaluationTypeListDto::new).collect(Collectors.toList());


        given(evaluationService.validate(any(UserInfo.class))).willReturn(1);
        given(evaluationService.evaluationCheck(anyLong(), anyInt())).willReturn(4);
        given(evaluationService.message(1)).willReturn(Maps.newHashMap(ImmutableMap.of("result", "success")));
        given(evaluationService.message(4)).willReturn(Maps.newHashMap(ImmutableMap.of("result", "value duple")));

        given(evaluationService.loadTypes(anyLong(), anyInt())).willReturn(
                Maps.newHashMap(ImmutableMap.of(
                        "load",
                        result,
                        "test",
                        "success")));

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.get("/api/boards/{board}/evaluations/{evaluation}/types", 1L, 1)
                        .with(csrf()).with(user(UserInfo.builder()
                                .auth("ROLE_USER")
                                .email("tester")
                                .build()
                        ))
                        .characterEncoding("utf-8")
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("evaluation-get-types",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("board").description("게시판 식별 번호"),
                                parameterWithName("evaluation").description("평가 기준표 식별 번호")
                        ),
                        responseFields(
                                fieldWithPath("load[]").description("평가기준표 지문 타입"),
                                fieldWithPath("load[].id").description("평가기준표 지문 타입 식별번호"),
                                fieldWithPath("load[].name").description("타입 이름"),
                                fieldWithPath("load[].gradations").description("타입 순서"),
                                fieldWithPath("test").description("성공 여부")
                        )
                ));
    }


}