package site.askephoenix.restapi.status.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.status.model.StatusInfo;
import site.askephoenix.restapi.status.service.StatusService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

@RestController
@RequestMapping("/api/status")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService service;

    @GetMapping("")
    public HashMap<String, Object> readStatus(
            @LoginUser UserInfo userInfo
            ) {
        return service.loadStatus(userInfo);
    }
}
