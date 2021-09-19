package site.askephoenix.restapi.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import site.askephoenix.restapi.user.service.UserService;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Mock
    HashMap<String, Object> resultMap;

    @Test
    @DisplayName("멤버 테스트")
    @WithMockUser(username = "kim-test", authorities = "ROLE_USER")
    void userTest() throws Exception {
        resultMap = Maps.newHashMap(ImmutableMap.of("test", "success"));
        mvc.perform(get("/user/test"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(Maps.newHashMap(ImmutableMap.of("test", "success")))));
    }

    @Test
    @DisplayName("토큰")
    void getToken() throws Exception {
        mvc.perform(get("/user/token"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패")
    void signup_fail() throws Exception {
        when(userService.save(any())).thenReturn(-1L);
        mvc.perform(post("/user").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(Maps.newHashMap(ImmutableMap.of("status", "fail")))
                ));
    }

    @Test
    @DisplayName("회원가입 성공")
    void signup_success() throws Exception {
        final Long user_id = 300L;
        when(userService.save(any())).thenReturn(user_id);
        mvc.perform(post("/user").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(Maps.newHashMap(ImmutableMap.of("create_user_id", user_id)))
                ));
    }

}