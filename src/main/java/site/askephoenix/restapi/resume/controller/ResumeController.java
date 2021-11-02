package site.askephoenix.restapi.resume.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.resume.dto.ResumeInfoDto;
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

    //이력서 입력 (저장)
    @PostMapping(value = "")
    public HashMap<String, Object> creatResume(ResumeInfoDto resumeInfoDto,@LoginUser UserInfo userInfo){
        Long id = resumeService.save(resumeInfoDto,userInfo);

        if (-1L == id) return Maps.newHashMap(ImmutableMap.of("status", "fail"));
        return Maps.newHashMap(ImmutableMap.of("create_resume_id", id));
    }

    //이력서 수정 (수정)
    @PutMapping(value = "")
    public HashMap<String, Object> modifyResume(
            ResumeInfoDto resumeInfoDto,
            @LoginUser UserInfo userInfo){
        Long id = resumeService.update(resumeInfoDto,userInfo);

        if (-1L == id) return Maps.newHashMap(ImmutableMap.of("status", "fail"));
        return Maps.newHashMap(ImmutableMap.of("modify_resume_id", id));
    }

    //이력서 삭제 (삭제)
    @DeleteMapping(value = "")
    public HashMap<String,Object> deleteResume(
            ResumeInfoDto resumeInfoDto,
            @LoginUser UserInfo userInfo
    ){
        Long id = resumeService.deleteResume(resumeInfoDto,userInfo);

        if (-1L == id) return Maps.newHashMap(ImmutableMap.of("status", "fail"));
        return Maps.newHashMap(ImmutableMap.of("delete_resume_id", id));
    }

    //내 이력서 조회
    @GetMapping(value = "/resume/{userId}")
    public ResumeInfoDto readResume(
            ResumeInfoDto resumeInfoDto,
            @PathVariable(name = "userId") Long userId
    ){
        return resumeService.readResumeInfo(resumeInfoDto , userId);
    }
}
