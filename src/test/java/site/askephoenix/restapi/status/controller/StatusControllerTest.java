package site.askephoenix.restapi.status.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import site.askephoenix.restapi.status.service.StatusService;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

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

    @DisplayName("사용자 상태 리스트 가져오기")
    void readStatus(){

    }



}