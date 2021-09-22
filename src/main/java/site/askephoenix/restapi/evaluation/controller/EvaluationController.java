package site.askephoenix.restapi.evaluation.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.evaluation.service.EvaluationService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EvaluationController {
    private final EvaluationService service;

    // 평가 지표 가져오기
    @GetMapping("/boards/{board}/evaluations/{evaluation}")
    public HashMap<String, Object> readEvaluationOfBoard(
            @LoginUser UserInfo userInfo,
            @PathVariable(name = "board") long board,
            @PathVariable(name = "evaluation") int evaluation) {

        // validate 전처리 작업
        final HashMap<String, Object> message = service.message(service.validate(userInfo));

        // validate 검사
        if (!"success".equals(message.get("result")))
            return message;

        // evaluation 가져오기
        try {
            return service.load(evaluation, board);
        } catch (NoSuchFieldException e) {
            return Maps.newHashMap(ImmutableMap.of("status", "not field"));
        }

    }
}
