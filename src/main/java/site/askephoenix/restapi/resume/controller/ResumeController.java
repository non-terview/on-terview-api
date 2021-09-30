package site.askephoenix.restapi.resume.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.resume.dto.ResumeInfoDto;
import site.askephoenix.restapi.resume.service.Impl.ResumeServiceImpl;
import site.askephoenix.restapi.resume.service.ResumeService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/resume")
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("/test")
    public HashMap<String, Object> testLogin(@LoginUser UserInfo userInfo) {
        return Maps.newHashMap(ImmutableMap.of("UserInfo", userInfo));
    }

    @PostMapping(value = "")
    public HashMap<String, Object> creatResume(ResumeInfoDto resumeInfoDto){
        Long id = resumeService.save(resumeInfoDto);

        if (-1L == id) return Maps.newHashMap(ImmutableMap.of("status", "fail"));
        return Maps.newHashMap(ImmutableMap.of("create_resume_id", id));
    }

}
