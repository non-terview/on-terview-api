package site.askephoenix.restapi.status.service.Impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.board.repository.BoardRepository;
import site.askephoenix.restapi.evaluation.service.Impl.EvaluationServiceImpl;
import site.askephoenix.restapi.status.dto.StatusInfoDto;
import site.askephoenix.restapi.status.model.StatusInfo;
import site.askephoenix.restapi.status.repository.StatusRepository;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatusServiceImplTest {

    @Mock
    StatusServiceImpl service;

    @Mock
    StatusRepository repository;

    @Mock
    BoardRepository boardRepository;

    @BeforeEach
    void setUp() {
        this.service = new StatusServiceImpl(
                repository,
                boardRepository
        );
    }

    @Test
    @DisplayName("LoadStatus 성공 테스트")
    void loadStatus() {
        final UserInfo userInfo = UserInfo.builder()
                .id(3L)
                .name("테스터1")
                .email("aistarteverything@gmail.com")
                .auth("RoleTest")
                .type("user")
                .build();
        final BoardInfo boardInfo1 = BoardInfo.builder()
                .seq(13L)
                .companyName("테스트 회사 A")
                .type("면접 게시판")
                .build();
        final BoardInfo boardInfo2 = BoardInfo.builder()
                .seq(13L)
                .companyName("테스트 회사 B")
                .type("면접 게시판")
                .build();

        final StatusInfo statusInfo1 = StatusInfo.builder()
                .id(132L)
                .userInfo(userInfo)
                .boardInfo(boardInfo1)
                .state("면접 취소됨")
                .isDeleted(false)
                .build();
        final StatusInfo statusInfo2 = StatusInfo.builder()
                .id(99L)
                .userInfo(userInfo)
                .boardInfo(boardInfo2)
                .state("준비됨")
                .isDeleted(false)
                .build();
        final List<StatusInfo> statusInfos = Lists.newArrayList(ImmutableList.of(statusInfo1,statusInfo2));
        final List<StatusInfoDto> info = statusInfos
                        .stream().map(StatusInfoDto::new).collect(Collectors.toList());

        HashMap<String, Object> result = Maps.newHashMap(ImmutableMap.of(
                "load", info, "test", "success"
        ));

        when(repository.findAllByUserInfo(any(UserInfo.class))).thenReturn(
                Optional.of(statusInfos)
        );

        assertEquals(service.loadStatus(userInfo).get("test"),result.get("test"));
    }


}