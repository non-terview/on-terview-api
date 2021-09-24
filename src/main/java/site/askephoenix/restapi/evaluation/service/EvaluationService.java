package site.askephoenix.restapi.evaluation.service;

import site.askephoenix.restapi.evaluation.dto.EvaluationInfoDto;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

public interface EvaluationService {
    int validate(UserInfo userInfo);

    HashMap<String, Object> message(int validate);

    HashMap<String, Object> load(int evaluation, long board ) throws NoSuchFieldException;

    HashMap<String, Object> save(int evaluation, long board, EvaluationInfoDto infoDto);
}
