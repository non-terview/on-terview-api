package site.askephoenix.restapi.user.controller;

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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import site.askephoenix.restapi.user.dto.UserInfoDto;
import site.askephoenix.restapi.user.dto.UserResultDto;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Mock
    UserInfo userInfo;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("로그인 정보 가져오기")
    void get_LoginUser() throws Exception {
        UserInfo info = UserInfo.builder()
                .id(1L)
                .name("김주윤")
                .type("USER")
                .email("aske@naver.com")
                .password( new BCryptPasswordEncoder().encode("passTests2123") )
                .auth("USER_ROLE")
                .build();

        given(userService.getUserDto(any(UserInfo.class))).willReturn(new UserResultDto(info));

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.get("/api/user")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
        );
        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-get",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("id").description("사용자 번호"),
                                fieldWithPath("name").description("이름"),
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("type").description("일반 사용자/회사 계정 구분")
                        )
                ));
    }

    @Test
    @DisplayName("회원가입 성공")
    void signup_success() throws Exception {

        UserInfoDto dto = new UserInfoDto();
        dto.setName("TestSuccess");
        dto.setPassword("success");
        dto.setEmail("aske@test.success");
        dto.setAuth("USER_ROLE");
        dto.setType("USER");

        final Long user_id = 300L;

        given(userService.save(any(UserInfoDto.class))).willReturn(user_id);

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.post("/api/user")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto))
                        .characterEncoding("utf-8")
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-post",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("id").description("사용자 식별번호 (null)"),
                                fieldWithPath("name").description("가입자 성명"),
                                fieldWithPath("password").description("가입자 비밀번호"),
                                fieldWithPath("auth").description("가입자 권한(관리자 : ROLE_ADMIN, 사용자 : ROLE_USER)"),
                                fieldWithPath("type").description("가입자 종류(USER, COMPANY)"),
                                fieldWithPath("email").description("가입자 이메일")
                        ),
                        responseFields(
                                fieldWithPath("create_user_id").type(JsonFieldType.NUMBER).description("사용자 번호")
                        )
                ));
    }

    @Test
    @DisplayName("회원 정보수정 성공")
    void updateSuccess() throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        UserInfo userInfo = UserInfo.builder()
                .id(100L)
                .auth("USER_ROLE")
                .type("USER")
                .email("aske")
                .name("TestUser")
                .password(encoder.encode("aske"))
                .build();

        given(userService.update(any(UserInfo.class), any(UserInfoDto.class)))
                .willReturn(100L)
        ;

        UserInfoDto dto = new UserInfoDto();
        dto.setName("TestSuccess");
        dto.setPassword("success");
        dto.setEmail("aske@test.success");

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.put("/api/user")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto))
                        .characterEncoding("utf-8")
                        .with(user(userInfo))
        );
        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-put",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint())
                                ,
                                requestParameters(
                                        parameterWithName("_csrf").description("인증 데이터")
                                ),
                                requestFields(
                                        fieldWithPath("id").description("사용자 식별번호 (null)"),
                                        fieldWithPath("name").description("변경할 성명"),
                                        fieldWithPath("password").description("변경할 비밀번호"),
                                        fieldWithPath("auth").description("사용자 권한 (null)"),
                                        fieldWithPath("type").description("사용자 종류 (null)"),
                                        fieldWithPath("email").description("변경할 이메일")
                                ),
                                responseFields(
                                        fieldWithPath("modify_user_id").description("변경된 계정 식별번호")
                                )
                        )
                );
    }

    @Test
    @DisplayName("회원가입 실패")
    void signup_fail() throws Exception {
        final Long user_id = -1L;

        given(userService.save(any(UserInfoDto.class))).willReturn(user_id);

        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.post("/api/user").with(csrf())
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-post-fail",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING).description("상태")
                                )
                        )
                );
    }

    @Test
    @DisplayName("토큰 가져오기")
    void getToken() throws Exception {
        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.get("/api/user/token")
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-get-token",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("token").type(JsonFieldType.OBJECT).description("토큰 정보"),
                                fieldWithPath("token.parameterName").type(JsonFieldType.STRING).description("파라미터 이름"),
                                fieldWithPath("token.headerName").type(JsonFieldType.STRING).description("헤더 이름"),
                                fieldWithPath("token.token").type(JsonFieldType.STRING).description("토큰 값")
                        )
                ));
    }

}
