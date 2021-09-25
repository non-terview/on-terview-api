package site.askephoenix.restapi.evaluation.service.Impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.board.repository.BoardRepository;
import site.askephoenix.restapi.evaluation.dto.EvaluationDetailInfoDto;
import site.askephoenix.restapi.evaluation.dto.EvaluationInfoDto;
import site.askephoenix.restapi.evaluation.dto.EvaluationTypeListDto;
import site.askephoenix.restapi.evaluation.model.EvaluationDetailInfo;
import site.askephoenix.restapi.evaluation.model.EvaluationInfo;
import site.askephoenix.restapi.evaluation.model.EvaluationTypeList;
import site.askephoenix.restapi.evaluation.repository.EvaluationDetailRepository;
import site.askephoenix.restapi.evaluation.repository.EvaluationRepository;
import site.askephoenix.restapi.evaluation.repository.EvaluationTypeListRepository;
import site.askephoenix.restapi.evaluation.service.EvaluationService;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.util.DefaultMessage;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EvaluationServiceImpl implements EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final EvaluationDetailRepository detailRepository;
    private final EvaluationTypeListRepository typeListRepository;
    private final BoardRepository boardRepository;

    @Override
    public int validate(UserInfo userInfo) {
        return userInfo == null ? -1 : 1;
    }


    @Override
    public int evaluationCheck(long board, int evaluation) {
        return evaluationRepository.findByBoardInfoAndGradations(
                boardRepository.findBySeq(board), evaluation
        ).stream().findAny().isPresent() ? 4 : 1;
    }


    @Override
    public HashMap<String, Object> message(int validate) {
        return Maps.newHashMap(
                ImmutableMap.of("result", DefaultMessage.getMessage(validate)));
    }

    @Override
    public HashMap<String, Object> load(int evaluation, long board) throws NoSuchFieldException {
        final EvaluationInfo evaluationInfo = evaluationRepository.findByBoardInfoAndGradations(
                boardRepository.findBySeq(board), evaluation).orElseThrow(NoSuchFieldException::new);
        return Maps.newHashMap(ImmutableMap.of("load", evaluationInfo, "test", "success"));
    }

    @Override
    public HashMap<String, Object> loadTypes(long board, int evaluation) throws NoSuchFieldException {
        final EvaluationInfo evaluationInfo = evaluationRepository.findByBoardInfoAndGradations(
                boardRepository.findBySeq(board), evaluation).orElseThrow(NoSuchFieldException::new);
        final EvaluationDetailInfo detailInfo = detailRepository.findByEvaluationInfoAndGradation(evaluationInfo, evaluation)
                .orElseThrow(NoSuchFieldException::new);
        final List<EvaluationTypeList> typeList =
                typeListRepository.findAllByEvaluationDetailInfo(detailInfo).orElseThrow(NoSuchFieldException::new);
        final List<EvaluationTypeListDto> result = typeList.stream().map(EvaluationTypeListDto::new).collect(Collectors.toList());
        return Maps.newHashMap(ImmutableMap.of(
                "load", result,
                "test", "success"));
    }

    @Override
    public HashMap<String, Object> save(int evaluation, long board, EvaluationInfoDto infoDto) {
        final List<EvaluationDetailInfoDto> detailInfoDto = infoDto.getDetails();

        final EvaluationInfo evaluationInfo = evaluationRepository.save(
                EvaluationInfo.builder()
                        .gradations(evaluation)
                        .boardInfo(boardRepository.findBySeq(board))
                        .title(infoDto.getTitle())
                        .isDeleted(false)
                        .build()
        );
        detailInfoDto.forEach(detail -> {
            final EvaluationDetailInfo detailInfo = detailRepository.save(EvaluationDetailInfo.builder()
                    .evaluationInfo(evaluationInfo)
                    .example(detail.getExample())
                    .score(detail.getScore())
                    .build()
            );
            detail.getType().forEach(type -> typeListRepository.save(
                    EvaluationTypeList.builder()
                            .evaluationDetailInfo(detailInfo)
                            .name(type.getName())
                            .gradations(type.getGradations())
                            .build()
            ));

        });

        return Maps.newHashMap(ImmutableMap.of("code", "success", "evaluation_key", evaluationInfo.getId()));
    }


}
