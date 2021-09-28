package site.askephoenix.restapi.status.service.Impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.board.repository.BoardRepository;
import site.askephoenix.restapi.status.dto.StatusInfoDto;
import site.askephoenix.restapi.status.model.StatusInfo;
import site.askephoenix.restapi.status.repository.StatusRepository;
import site.askephoenix.restapi.status.service.StatusService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository repository;
    private final BoardRepository boardRepository;

    @Override
    public HashMap<String, Object> loadStatus(UserInfo userInfo) {
        final List<StatusInfo> info = repository.findAllByUserInfo(userInfo).orElseGet(ArrayList::new);
        final List<StatusInfoDto> dto = info.stream().map(StatusInfoDto::new).collect(Collectors.toList());

        return Maps.newHashMap(ImmutableMap.of("load", dto, "test", "success"));
    }


    @Override
    public HashMap<String, Object> loadTargetBoard(UserInfo userInfo, Long board, StatusInfoDto statusInfoDto) {
        final Optional<StatusInfo> statusInfo = repository.findByUserInfoAndBoardInfo(userInfo, boardRepository.findBySeq(board));
        final StatusInfoDto dto = new StatusInfoDto(statusInfo.orElseGet( () -> StatusInfo.builder().build() ));

        return Maps.newHashMap(ImmutableMap.of(
                "load", dto,
                "code","success"
        ));
    }
}
