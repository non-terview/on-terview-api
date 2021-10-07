package site.askephoenix.restapi.resume.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.resume.dto.ResumeInfoDto;
import site.askephoenix.restapi.resume.model.ResumeInfo;
import site.askephoenix.restapi.resume.service.Impl.ResumeServiceImpl;
import site.askephoenix.restapi.resume.service.ResumeService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/resume")
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("/test")
    public HashMap<String, Object> testLogin(@LoginUser UserInfo userInfo) {
        return Maps.newHashMap(ImmutableMap.of("UserInfo", userInfo));
    }

    @PostMapping(value = "")
    public HashMap<String, Object> creatResume(ResumeInfoDto resumeInfoDto,@LoginUser UserInfo userInfo){
        Long id = resumeService.save(resumeInfoDto,userInfo);

        if (-1L == id) return Maps.newHashMap(ImmutableMap.of("status", "fail"));
        return Maps.newHashMap(ImmutableMap.of("create_resume_id", id));
    }

    @PutMapping(value = "")
    public HashMap<String, Object> modifyResume(ResumeInfo resumeInfo, ResumeInfoDto resumeInfoDto, @LoginUser UserInfo userInfo){

        Long id = resumeService.update(resumeInfo,resumeInfoDto,userInfo);


        if (-1L == id) return Maps.newHashMap(ImmutableMap.of("status", "fail"));
        return Maps.newHashMap(ImmutableMap.of("modify_resume_id", id));
    }

}
