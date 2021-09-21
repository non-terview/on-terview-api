package site.askephoenix.restapi.evaluation.service.Impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.evaluation.service.EvaluationService;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.util.DefaultMessage;

import java.util.HashMap;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Override
    public int validate(UserInfo userInfo) {
        return userInfo == null ? -1 : 1;
    }

    @Override
    public HashMap<String, Object> message(int validate) {
        return Maps.newHashMap(
                ImmutableMap.of("result", DefaultMessage.getMessage(validate)));
    }

    @Override
    public HashMap<String, Object> load(Integer board, Integer evaluation) {
        return null;
    }


}
