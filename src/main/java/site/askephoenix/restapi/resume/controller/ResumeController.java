package site.askephoenix.restapi.resume.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.askephoenix.restapi.resume.dto.ResumeInfoDto;
import site.askephoenix.restapi.resume.service.ResumeService;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/resume")
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping(value = "/test")
    public HashMap<String, Object> userTest() {
        return Maps.newHashMap(ImmutableMap.of("test", "success"));
    }

    @PostMapping(value = "")
    public HashMap<String, Object> register(ResumeInfoDto resumeInfoDto){
        Long id = ResumeService.save(resumeInfoDto);

        return null;
    }
}
