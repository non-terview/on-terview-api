package site.askephoenix.restapi.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import site.askephoenix.restapi.board.service.BoardService;
import site.askephoenix.restapi.user.service.UserService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
class BoardControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    BoardService boardService;

    // 시큐리티 설정으로 인해 UserService 를 간접적으로 사용하기 때문에 작성하였습니다.
    @MockBean
    UserService userService;

    @Test
    @DisplayName("페이지 리스트 테스트")
    @WithMockUser(username = "kim-test", authorities = "ROLE_USER")
    void list() throws Exception {
        // 서비스에서 보드 페이지를 가져오는 부분만 실행되는지 테스트합니다.
        // 만약 아니라면 해당 컨트롤러는 다른 서비스 메소드 조건을 충족할 수 없어 실패합니다.
        when(boardService.getBoardPage(anyInt())).thenReturn(
                Maps.newHashMap(ImmutableMap.of("test","success"))
        );
        // 컨트롤러를 테스트합니다.
        mvc.perform(get("/api/board/list/1").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(Maps.newHashMap(ImmutableMap.of("test", "success")))));
    }
}