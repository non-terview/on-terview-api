package site.askephoenix.restapi.user.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.user.dto.UserInfoDto;
import site.askephoenix.restapi.user.dto.UserResultDto;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.service.UserService;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/test")
    public HashMap<String, Object> userTest() {
        return Maps.newHashMap(ImmutableMap.of("test", "success"));
    }

    @GetMapping(value = "/token")
    public HashMap<String, Object> getToken(CsrfToken token) {
        return Maps.newHashMap(ImmutableMap.of(
                        "token", token
                ));
    }

    @GetMapping(value = "")
    public UserResultDto getUserInfo(@LoginUser UserInfo userInfo){
        return userService.getUserDto(userInfo);
    }

    @PostMapping(value = "")
    public HashMap<String, Object> signup(UserInfoDto infoDto) {
        Long id = userService.save(infoDto);
//            이미 이메일이 있는 경우
        if (-1L == id) return Maps.newHashMap(ImmutableMap.of("status", "fail"));
        return Maps.newHashMap(ImmutableMap.of("create_user_id", id));
    }


    @PutMapping(value = "")
    public HashMap<String, Object> update(
            @LoginUser UserInfo userInfo,
            UserInfoDto infoDto){
        Long id = userService.update(userInfo,infoDto);

        //이메일이 없는 경우
        if (-1L == id) return Maps.newHashMap(ImmutableMap.of("status", "fail"));
        return Maps.newHashMap(ImmutableMap.of("modify_user_id", id));
    }



}
