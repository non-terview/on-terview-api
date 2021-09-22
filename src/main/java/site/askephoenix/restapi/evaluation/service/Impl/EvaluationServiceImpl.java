package site.askephoenix.restapi.evaluation.service.Impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.board.repository.BoardRepository;
import site.askephoenix.restapi.evaluation.model.EvaluationInfo;
import site.askephoenix.restapi.evaluation.repository.EvaluationRepository;
import site.askephoenix.restapi.evaluation.service.EvaluationService;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.util.DefaultMessage;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class EvaluationServiceImpl implements EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final BoardRepository boardRepository;

    @Override
    public int validate(UserInfo userInfo) {
        return userInfo == null ? -1 : 1;
    }

    @Override
    public HashMap<String, Object> save() {
        return null;
    }

    @Override
    public HashMap<String, Object> message(int validate) {
        return Maps.newHashMap(
                ImmutableMap.of("result", DefaultMessage.getMessage(validate)));
    }

    @Override
    public HashMap<String, Object> load(int evaluation,long board) throws NoSuchFieldException {
        EvaluationInfo evaluationInfo = evaluationRepository.findByBoardInfoAndGradations(
                boardRepository.findBySeq(board) , evaluation ).orElseThrow(NoSuchFieldException::new);
        return Maps.newHashMap(ImmutableMap.of("load", evaluationInfo, "test", "success"));
    }

}
