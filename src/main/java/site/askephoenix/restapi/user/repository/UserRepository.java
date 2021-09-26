package site.askephoenix.restapi.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email);

}
