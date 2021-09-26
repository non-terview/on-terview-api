package site.askephoenix.restapi.status.service.Impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.status.dto.StatusInfoDto;
import site.askephoenix.restapi.status.model.StatusInfo;
import site.askephoenix.restapi.status.repository.StatusRepository;
import site.askephoenix.restapi.status.service.StatusService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository repository;


    @Override
    public HashMap<String, Object> loadStatus(UserInfo userInfo) {
        final List<StatusInfo> info = repository.findAllByUserInfo(userInfo).orElseGet(ArrayList::new);
        final List<StatusInfoDto> dto = info.stream().map(StatusInfoDto::new).collect(Collectors.toList());

        return Maps.newHashMap(ImmutableMap.of("load", dto, "test", "success"));
    }
}
