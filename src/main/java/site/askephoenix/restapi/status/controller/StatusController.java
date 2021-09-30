package site.askephoenix.restapi.status.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.status.dto.StatusInfoDto;
import site.askephoenix.restapi.status.service.StatusService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

@RestController
@RequestMapping("/api/status")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService service;

    // 해당 사용자의 status 값 전부
    @GetMapping("")
    public HashMap<String, Object> readStatus(
            @LoginUser UserInfo userInfo
    ) {
        return service.loadStatus(userInfo);
    }

    // 해당 사용자의 특정 board 의 status 값 전부
    @GetMapping("/boards/{board}")
    public HashMap<String, Object> readTargetBoard(
            @LoginUser UserInfo userInfo,
            @PathVariable(name = "board") Long board,
            StatusInfoDto statusInfoDto
    ){
        return service.loadTargetBoard(userInfo, board,statusInfoDto);
    }

}
