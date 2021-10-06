package site.askephoenix.restapi.status.service;

import site.askephoenix.restapi.status.dto.StatusInfoDto;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

public interface StatusService {
    HashMap<String, Object> loadStatus(UserInfo userInfo);

    HashMap<String, Object> loadTargetBoard(UserInfo userInfo, Long board, StatusInfoDto statusInfoDto);
}
