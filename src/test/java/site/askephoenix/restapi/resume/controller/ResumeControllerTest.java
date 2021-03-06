package site.askephoenix.restapi.resume.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
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
import site.askephoenix.restapi.company_test.controller.CompanyTestsController;
import site.askephoenix.restapi.resume.dto.ResumeInfoDto;
import site.askephoenix.restapi.resume.model.ResumeInfo;
import site.askephoenix.restapi.resume.service.ResumeService;
import site.askephoenix.restapi.user.dto.UserResultDto;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(ResumeController.class)
class ResumeControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @MockBean
    ResumeService resumeService;

    @Mock
    UserInfo userInfo;

    @Mock
    UserResultDto userDto;

    @Mock
    ResumeInfo info;

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


    }

    @Test
    @DisplayName("Test")
    void testLogin() {
    }

    @Test
    @DisplayName("????????? ????????? : POST")
    void creatResume() throws Exception {
        ResumeInfo info = ResumeInfo.builder()
                .userInfo(userInfo)
                .title("?????? ????????? ?????????")
                .introduction("spring, jsp, php ??? ?????? ?????? ??????????????? 5??? ??? ???????????? ??????????????????.")
                .final_edu("4?????? ?????? ??????")
                .career("5??? System Instruct ?????? ?????????")
                .certificate("??????????????????,?????????????????? 1/2???")
                .portfolio("www.askephoenix.site, github.askehoenix.io")
                .job("?????????")
                .isDeleted(false)
                .build();
        ResumeInfoDto dto = new ResumeInfoDto(info);
        given(resumeService.save(any(ResumeInfoDto.class), any(UserInfo.class))).willReturn(1L);
        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.post(
                                "/api/resume")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto))
                        .characterEncoding("utf-8")
                        .with(csrf()).with(user(userInfo))
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("resume-post",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("_csrf").description("?????? ?????????")
                        ),
                        requestFields(
                                fieldWithPath("id").description("????????? ???????????? (??????)"),
                                fieldWithPath("userInfoDto.id").description("????????? ????????????"),
                                fieldWithPath("userInfoDto.name").description("????????? ??????"),
                                fieldWithPath("userInfoDto.email").description("????????? ?????????"),
                                fieldWithPath("userInfoDto.type").description("????????? ??????(??????/??????)"),
                                fieldWithPath("title").description("????????? ??????"),
                                fieldWithPath("introduction").description("????????? ??????"),
                                fieldWithPath("final_edu").description("????????????"),
                                fieldWithPath("edu_status").description("?????? ?????? ?????? ex(??????,??????,??????)"),
                                fieldWithPath("career").description("??????"),
                                fieldWithPath("certificate").description("?????????"),
                                fieldWithPath("portfolio").description("???????????????"),
                                fieldWithPath("job").description("?????? ??????"),
                                fieldWithPath("createDate").description("(??????)"),
                                fieldWithPath("updateDate").description("(??????)"),
                                fieldWithPath("deleted").description("????????? ?????? ??????")
                        ),
                        responseFields(
                                fieldWithPath("create_resume_id").description("???????????? ????????? ????????????")
                        )
                ));
    }

    @Test
    @DisplayName("????????? ???????????? : PUT")
    void modifyResume() throws Exception {
        ResumeInfo info = ResumeInfo.builder()
                .userInfo(userInfo)
                .title("?????? ????????? ?????????")
                .introduction("spring, jsp, php ??? ?????? ?????? ??????????????? 5??? ??? ???????????? ??????????????????.")
                .final_edu("4?????? ?????? ??????")
                .career("5??? System Instruct ?????? ?????????")
                .certificate("??????????????????,?????????????????? 1/2???")
                .portfolio("www.askephoenix.site, github.askehoenix.io")
                .job("?????????")
                .isDeleted(false)
                .build();
        ResumeInfoDto dto = new ResumeInfoDto(info);
        given(resumeService.update(any(ResumeInfoDto.class), any(UserInfo.class))).willReturn(1L);
        ResultActions perform = this.mvc.perform(
                RestDocumentationRequestBuilders.put(
                                "/api/resume")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto))
                        .characterEncoding("utf-8")
                        .with(csrf()).with(user(userInfo))
        );

        perform.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("resume-put",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("_csrf").description("?????? ?????????")
                        ),
                        requestFields(
                                fieldWithPath("id").description("????????? ???????????? (??????)"),
                                fieldWithPath("userInfoDto.id").description("????????? ????????????"),
                                fieldWithPath("userInfoDto.name").description("????????? ??????"),
                                fieldWithPath("userInfoDto.email").description("????????? ?????????"),
                                fieldWithPath("userInfoDto.type").description("????????? ??????(??????/??????)"),
                                fieldWithPath("title").description("????????? ??????"),
                                fieldWithPath("introduction").description("????????? ??????"),
                                fieldWithPath("final_edu").description("????????????"),
                                fieldWithPath("edu_status").description("?????? ?????? ?????? ex(??????,??????,??????)"),
                                fieldWithPath("career").description("??????"),
                                fieldWithPath("certificate").description("?????????"),
                                fieldWithPath("portfolio").description("???????????????"),
                                fieldWithPath("job").description("?????? ??????"),
                                fieldWithPath("createDate").description("(??????)"),
                                fieldWithPath("updateDate").description("(??????)"),
                                fieldWithPath("deleted").description("????????? ?????? ??????")
                        ),
                        responseFields(
                                fieldWithPath("modify_resume_id").description("????????? ????????? ????????????")
                        )
                ));
    }
}