package site.askephoenix.restapi.evaluation.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.evaluation.dto.EvaluationDetailInfoDto;
import site.askephoenix.restapi.evaluation.dto.EvaluationInfoDto;
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

    // 평가지표 create : post
    @PostMapping("/boards/{board}/evaluations/{evaluation}")
    public HashMap<String, Object> createEvaluation(
            @LoginUser UserInfo userInfo,
            @PathVariable(name = "board") long board,
            @PathVariable(name = "evaluation") int evaluation,
            @RequestBody EvaluationInfoDto infoDto
            ) {

        // validate
        final HashMap<String, Object> message = service.message(service.validate(userInfo));

        // exception validate
        if (!"success".equals(message.get("result")))
            return message;

        // create evaluation
        try {
            return service.save(evaluation, board, infoDto);
        } catch (Exception e) {
            // exception create evaluation
            return Maps.newHashMap(ImmutableMap.of("status", "not field"));
        }
    }





}
