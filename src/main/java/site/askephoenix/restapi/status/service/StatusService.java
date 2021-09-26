package site.askephoenix.restapi.status.service;

import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

public interface StatusService {
    HashMap<String, Object> loadStatus(UserInfo userInfo);
}
