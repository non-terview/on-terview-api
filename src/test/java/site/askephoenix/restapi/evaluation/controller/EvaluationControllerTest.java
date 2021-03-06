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
    @DisplayName("?????? ?????? ???????????? Success")
    void readEvaluationOfBoard() throws Exception {
        BoardInfo testBoard = BoardInfo.builder()
                .seq(1L)
                .title("????????? ??????????????????.")
                .writer("????????? ??????")
                .category("?????? ?????????")
                .companyName("????????? ??????")
                .content("??????")
                .isDeleted(false)
                .type("?????????")
                .createDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .build();
        EvaluationInfo testEvaluation = EvaluationInfo.builder()
                .id(1L)
                .gradations(1)
                .title("????????? ?????? 1")
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
                                parameterWithName("board").description("????????? ?????? ??????"),
                                parameterWithName("evaluation").description("?????? ????????? ?????? ??????")
                        ),
                        responseFields(
                                fieldWithPath("load").type(JsonFieldType.OBJECT).description("?????? map"),
                                fieldWithPath("load.id").description("?????? ????????? ?????????"),
                                fieldWithPath("load.boardInfo").description("?????? ???????????? ????????? ?????????"),
                                fieldWithPath("load.boardInfo.seq").description("????????? ?????????"),
                                fieldWithPath("load.boardInfo.title").description("????????? ??????"),
                                fieldWithPath("load.boardInfo.writer").description("????????? ?????????"),
                                fieldWithPath("load.boardInfo.category").description("????????? ????????????"),
                                fieldWithPath("load.boardInfo.companyName").description("????????? ?????? ?????????"),
                                fieldWithPath("load.boardInfo.content").description("????????? ??????"),
                                fieldWithPath("load.boardInfo.deleted").description("????????? ?????? ??????(true ???, ????????????)"),
                                fieldWithPath("load.boardInfo.type").description("????????? ??????(??????, ????????????)"),
                                fieldWithPath("load.boardInfo.createDate").description("????????? ?????? ??????"),
                                fieldWithPath("load.boardInfo.updateDate").description("????????? ?????? ??????"),
                                fieldWithPath("load.gradations").description("?????? ????????? ??????"),
                                fieldWithPath("load.title").description("?????? ????????? ??????"),
                                fieldWithPath("load.createDate").description("?????? ????????? ?????? ??????"),
                                fieldWithPath("load.updateDate").description("?????? ????????? ????????? ??????"),
                                fieldWithPath("load.deleted").description("?????? ????????? ?????? ??????"),
                                fieldWithPath("test").description("????????? ?????? ??????")
                        )
                ));
    }

    @Test
    @DisplayName("?????? ?????? ???????????? Success")
    void createEvaluation() throws Exception {
        BoardInfo boardInfo = BoardInfo.builder()
                .seq(1L)
                .companyName("(???)????????????")
                .type("?????? ?????????")
                .category("?????? ??????")
                .writer("????????? A")
                .title("????????? ??? ?????? ????????? ????????????.")
                .content("~~~~ ?????? ???????????? ?????? ?????? ???????????? ???????????????.")
                .isDeleted(false)
                .build();
        EvaluationInfo evaluationInfo = EvaluationInfo.builder()
                .id(3L)
                .boardInfo(boardInfo)
                .gradations(3)
                .title("???????????? ?????? ??????")
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
                                        parameterWithName("board").description("????????? ?????? ??????"),
                                        parameterWithName("evaluation").description("?????? ????????? ?????? ??????")
                                ),
                                requestParameters(
                                        parameterWithName("_csrf").description("?????? ?????????")
                                ),
                                requestFields(
                                        fieldWithPath("infoDto").description("???????????? data"),
                                        fieldWithPath("infoDto.details[]").description("??????????????? ???????????? ?????? ?????????"),
                                        fieldWithPath("infoDto.id").description("???????????? - ?????? ??????"),
                                        fieldWithPath("infoDto.boardDto").description("????????? ?????? data"),
                                        fieldWithPath("infoDto.boardDto.seq").description("????????? ?????? - ??????"),
                                        fieldWithPath("infoDto.boardDto.company").description("????????? ?????? - ?????????"),
                                        fieldWithPath("infoDto.boardDto.type").description("????????? ?????? - ????????? ?????? ??????"),
                                        fieldWithPath("infoDto.boardDto.category").description("????????? ?????? - ????????? ??????"),
                                        fieldWithPath("infoDto.boardDto.writer").description("????????? ?????? - ?????????"),
                                        fieldWithPath("infoDto.boardDto.title").description("????????? ?????? - ??????"),
                                        fieldWithPath("infoDto.boardDto.content").description("????????? ?????? - ??????"),
                                        fieldWithPath("infoDto.boardDto.deleted").description("????????? ?????? - ?????? ??????"),

                                        fieldWithPath("infoDto.gradations").description("???????????? - ????????????"),
                                        fieldWithPath("infoDto.title").description("???????????? - ??????"),
                                        fieldWithPath("infoDto.deleted").description("???????????? - ?????? ??????")

                                ),
                                responseFields(
                                        fieldWithPath("evaluation_key").description("????????? ???????????? ????????????"),
                                        fieldWithPath("code").description("?????? ??????")
                                )
                        )
                );
    }

    @Test
    @DisplayName("?????? ?????? ????????? ?????? ???????????? Success")
    void readEvaluationType() throws Exception {

        EvaluationTypeList typeList1 = EvaluationTypeList.builder()
                .id(3000L)
                .name("?????? ??????")
                .gradations(1)
                .build();
        EvaluationTypeList typeList2 = EvaluationTypeList.builder()
                .id(3002L)
                .name("??????")
                .gradations(2)
                .build();
        EvaluationTypeList typeList3 = EvaluationTypeList.builder()
                .id(3006L)
                .name("??????")
                .gradations(3)
                .build();
        EvaluationTypeList typeList4 = EvaluationTypeList.builder()
                .id(2001L)
                .name("??????")
                .gradations(4)
                .build();
        EvaluationTypeList typeList5 = EvaluationTypeList.builder()
                .id(300L)
                .name("?????? ??????")
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
                                parameterWithName("board").description("????????? ?????? ??????"),
                                parameterWithName("evaluation").description("?????? ????????? ?????? ??????")
                        ),
                        responseFields(
                                fieldWithPath("load[]").description("??????????????? ?????? ??????"),
                                fieldWithPath("load[].id").description("??????????????? ?????? ?????? ????????????"),
                                fieldWithPath("load[].name").description("?????? ??????"),
                                fieldWithPath("load[].gradations").description("?????? ??????"),
                                fieldWithPath("test").description("?????? ??????")
                        )
                ));
    }


}