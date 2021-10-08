package site.askephoenix.restapi.resume;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.board.repository.BoardRepository;
import site.askephoenix.restapi.resume.dto.ResumeInfoDto;
import site.askephoenix.restapi.resume.model.ResumeInfo;
import site.askephoenix.restapi.resume.repository.ResumeRepository;
import site.askephoenix.restapi.resume.service.ResumeService;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.repository.UserRepository;

import java.util.Optional;
import java.util.stream.IntStream;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ResumeTest {

    @Autowired
    private ResumeService resumeService;
    @Autowired
    private ResumeRepository repository;
    @Autowired
    private UserRepository userRepository;

    /* @Test
     public void resumeUpdateTest(){
         ResumeInfo info = repository.getById(1L);
         ResumeInfoDto dto = new ResumeInfoDto(info.builder().isDeleted(true).build());
         UserInfo userInfo = userRepository.searchById(1L);
         resumeService.update(info, dto, userInfo);
     }
 */
/*    public void insertResumeTest() {
        IntStream.range(0,22).forEach(i->{
            ResumeInfo info = ResumeInfo.builder().title(i+"번째임").build();
            repository.save(info);
        });
    }*/
/*
    @Test
    public void somethingTest() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<CompanyBoardInfo> results = repo.findByWriterAndAndCompanyNameContainingOrderBySeq("16", pageable);
        results.getContent().forEach(System.out::println);

    }
*/


}
