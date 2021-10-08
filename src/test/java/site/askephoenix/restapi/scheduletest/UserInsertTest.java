package site.askephoenix.restapi.scheduletest;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import site.askephoenix.restapi.page.dto.PageDto;
import site.askephoenix.restapi.page.vo.PageVO;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.repository.UserRepository;
import site.askephoenix.restapi.user.service.UserService;


import java.util.stream.IntStream;

@ExtendWith({SpringExtension.class})
@SpringBootTest
public class UserInsertTest {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService service;

/*
    @Test
    public void insertTest() {
        IntStream.range(0,22).forEach(i->{
            repository.save(UserInfo.builder().email(i+"번쨰유저이메일").password(i+"111").auth("user").name(i+"번째 유저").build());
        });
    }*/


  /*  @Test
    public void pageTest() {

        Pageable pageable = PageRequest.of(0, 10);
      //  Page<UserInfo> page = repository.findAllByNameContaining("번째", pageable);
        Page<UserInfo> page = repository.findAllById(1L, pageable);
        page.getContent().forEach(userInfo -> {
            System.out.println(userInfo.getEmail() + userInfo.getName());
        });
    }
*/
  /*  @Test
    public void getUserIdTest() {
        UserDetails details = service.loadUserByUsername("1번쨰유저이메일");
        System.out.println(details.getPassword());
    }
*/
}
