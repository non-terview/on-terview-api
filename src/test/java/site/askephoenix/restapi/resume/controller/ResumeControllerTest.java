package site.askephoenix.restapi.resume.controller;

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
                .type("").name("김주윤").build();
        this.userDto = new UserResultDto(userInfo);


    }

    @Test
    @DisplayName("Test")
    void testLogin() {
    }

    @Test
    @DisplayName("이력서 만들기 : POST")
    void creatResume() throws Exception {
        ResumeInfo info = ResumeInfo.builder()
                .userInfo(userInfo)
                .title("젊고 건강한 개발자")
                .introduction("spring, jsp, php 를 통해 여러 프로젝트를 5년 간 개발하고 운영했습니다.")
                .final_edu("4년제 대학 졸업")
                .career("5년 System Instruct 기업 정규직")
                .certificate("정보처리기사,리눅스마스터 1/2급")
                .portfolio("www.askephoenix.site, github.askehoenix.io")
                .job("개발자")
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
                                parameterWithName("_csrf").description("인증 데이터")
                        ),
                        requestFields(
                                fieldWithPath("id").description("이력서 식별번호 (빈칸)"),
                                fieldWithPath("userInfoDto.id").description("작성자 식별번호"),
                                fieldWithPath("userInfoDto.name").description("작성자 이름"),
                                fieldWithPath("title").description("이력서 제목"),
                                fieldWithPath("introduction").description("간단한 소개"),
                                fieldWithPath("final_edu").description("최종학력"),
                                fieldWithPath("edu_status").description("최종 학력 상태 ex(재학,졸업,휴학)"),
                                fieldWithPath("career").description("경력"),
                                fieldWithPath("certificate").description("자격증"),
                                fieldWithPath("portfolio").description("포트폴리오"),
                                fieldWithPath("job").description("희망 업종"),
                                fieldWithPath("createDate").description("이력서 생성 날짜"),
                                fieldWithPath("updateDate").description("이력서 수정 날짜"),
                                fieldWithPath("deleted").description("이력서 삭제 상태")
                        ),
                        responseFields(
                                fieldWithPath("create_resume_id").description("만들어진 이력서 식별번호")
                        )
                ));
    }

    @Test
    @DisplayName("이력서 수정하기 : PUT")
    void modifyResume() {
    }
}