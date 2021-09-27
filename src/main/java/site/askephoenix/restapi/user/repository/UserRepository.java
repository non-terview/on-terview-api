package site.askephoenix.restapi.user.repository;


import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email);

    @NonNull
    Optional<UserInfo> findById(Long id);

}
