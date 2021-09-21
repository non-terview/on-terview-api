package site.askephoenix.restapi.evaluation.service.Impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EvaluationServiceImplTest {
    // 해당 테스트는 서비스 테스트로 빈 테스트는 제외합니다.
    // 이유 : 빈테스트는 오래 걸리며, 개발하며 작성되는 테스트이기 때문에
    //   컨트롤러 테스트에서 빈테스트로 종합적으로 확인하면 됩니다.

    @Mock
    EvaluationServiceImpl evaluationService;

    @BeforeEach
    void setEvaluationService() {
        evaluationService = new EvaluationServiceImpl();
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


}