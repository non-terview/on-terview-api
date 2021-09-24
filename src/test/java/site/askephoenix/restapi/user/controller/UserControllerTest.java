package site.askephoenix.restapi.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import site.askephoenix.restapi.user.dto.UserInfoDto;
import site.askephoenix.restapi.user.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("회원가입 성공")
    void signup_success() throws Exception {
        final Long user_id = 300L;

        given(userService.save(any(UserInfoDto.class))).willReturn(user_id);

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.post("/user").with(csrf())
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-post",
                        responseFields(
                                fieldWithPath("create_user_id").description("사용자 번호")
                        )
                ));
    }

    @Test
    @DisplayName("회원가입 실패")
    void signup_fail() throws Exception {
        final Long user_id = -1L;

        given(userService.save(any(UserInfoDto.class))).willReturn(user_id);

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.post("/user").with(csrf())
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-post-fail",
                                responseFields(
                                        fieldWithPath("status").description("상태")
                                )
                        )
                );
    }


//
//        when(userService.save(any())).thenReturn(user_id);
//        mvc.perform(post("/user").with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(content().json(
//                        new ObjectMapper().writeValueAsString(Maps.newHashMap(ImmutableMap.of("create_user_id", user_id)))
//                ));

//
//    @Test
//    @DisplayName("멤버 테스트")
//    @WithMockUser(username = "kim-test", authorities = "ROLE_USER")
//    void userTest() throws Exception {
//        mvc.perform(get("/user/test"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(
//                        new ObjectMapper().writeValueAsString(Maps.newHashMap(ImmutableMap.of("test", "success")))));
//    }
//
//    @Test
//    @DisplayName("토큰")
//    void getToken() throws Exception {
//        mvc.perform(get("/user/token"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("회원가입 실패")
//    void signup_fail() throws Exception {
//        when(userService.save(any())).thenReturn(-1L);
//        mvc.perform(post("/user").with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(content().json(
//                        new ObjectMapper().writeValueAsString(Maps.newHashMap(ImmutableMap.of("status", "fail")))
//                ));
//    }


}
