package site.askephoenix.restapi.evaluation.service.Impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.askephoenix.restapi.board.repository.BoardRepository;
import site.askephoenix.restapi.evaluation.model.EvaluationInfo;
import site.askephoenix.restapi.evaluation.repository.EvaluationRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EvaluationServiceImplTest {
    // 해당 테스트는 서비스 테스트로 빈 테스트는 제외합니다.
    // 이유 : 빈테스트는 오래 걸리며, 개발하며 작성되는 테스트이기 때문에
    //   컨트롤러 테스트에서 빈테스트로 종합적으로 확인하면 됩니다.

    @Mock
    EvaluationServiceImpl evaluationService;

    @Mock
    EvaluationRepository evaluationRepository;

    @Mock
    BoardRepository boardRepository;

    @BeforeEach
    void setUp() {
        this.evaluationService = new EvaluationServiceImpl(
                evaluationRepository,
                boardRepository
        );
    }

    @Test
    @DisplayName("사용자가 없으면 유효성 검사 결과는 -1인지")
    void validate() {
        assertEquals(evaluationService.validate(null), -1);
    }

    @Test
    @DisplayName("사용자가 없으면 / result : not login / map 을 반환하는지 ")
    void message() {
        assertEquals(evaluationService.message(-1),
                Maps.newHashMap(ImmutableMap.of("result", "not login")));
    }

    @Test
    @DisplayName("board 를 통해서 evaluation 가져오는지")
    void load() throws NoSuchFieldException {
        final int evaluation = 1;
        final long board = 1L;
        when(evaluationRepository.findByBoardInfoAndGradations(any(), anyInt()))
                .thenReturn(
                        Optional.of(EvaluationInfo.builder()
                                .id(1L)
                                .gradations(123).build())
                );
        assertEquals(evaluationService.load(evaluation,board).get("test"), "success");
    }

}