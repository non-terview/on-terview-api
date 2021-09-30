package site.askephoenix.restapi.status.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.status.dto.StatusInfoDto;
import site.askephoenix.restapi.status.model.StatusInfo;
import site.askephoenix.restapi.status.service.StatusService;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(StatusController.class)
class StatusControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    StatusService service;

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
    @DisplayName("사용자 상태 리스트 가져오기")
    void readStatus() throws Exception {
//        final List<StatusInfo> info = repository.findAllByUserInfo(userInfo).orElseGet(ArrayList::new);
//        final List<StatusInfoDto> dto = info.stream().map(StatusInfoDto::new).collect(Collectors.toList());
//
//        return Maps.newHashMap(ImmutableMap.of("load", dto, "test", "success"));

        // 반환 statusInfo -> List -> Dto
        UserInfo userInfo = UserInfo.builder()
                .build();
        StatusInfo statusInfo1 = StatusInfo.builder()
                .id(1L)
                .state("ready")
                .isDeleted(false)
                .userInfo(userInfo)
                .build();
        StatusInfo statusInfo2 = StatusInfo.builder()
                .id(9L)
                .state("ready")
                .isDeleted(false)
                .userInfo(userInfo)
                .build();
        StatusInfo statusInfo3 = StatusInfo.builder()
                .id(2L)
                .state("ready")
                .isDeleted(false)
                .userInfo(userInfo)
                .build();
        final List<StatusInfoDto> dto = Lists.newArrayList(ImmutableList.of(statusInfo1, statusInfo2, statusInfo3)).stream().map(StatusInfoDto::new)
                .collect(Collectors.toList());


        final HashMap<String, Object> result = Maps.newHashMap(ImmutableMap.of("load", dto, "test", "success"));

        given(service.loadStatus(any(UserInfo.class))).willReturn(result);

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.get("/api/status")
                        .with(csrf()).with(user(UserInfo.builder()
                                .auth("ROLE_USER")
                                .email("tester")
                                .build()
                        ))
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("status-get",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("load[]").description("상태(status) 정보 map"),
                                fieldWithPath("load[].userInfoDto").description("해당 사용자"),
                                fieldWithPath("load[].boardDto").description("해당 게시판"),
                                fieldWithPath("load[].id").description("상태 식별자 번호"),
                                fieldWithPath("load[].state").description("상태 값"),
                                fieldWithPath("load[].deleted").description("삭제 여부"),
                                fieldWithPath("test").description("test 결과")
                        )

                ));
    }


}