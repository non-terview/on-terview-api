package site.askephoenix.restapi.user.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByEmail(String email);

    //유저 페이징을 위한 코드입니다 테스트용
    Page<UserInfo> findAllByNameContaining(String name, Pageable pageable);

    Page<UserInfo> findAllById(Long id, Pageable pageable);

    @Query(value = "select u from UserInfo u where u.id = ?1 ")
    UserInfo searchById(Long id);
}
