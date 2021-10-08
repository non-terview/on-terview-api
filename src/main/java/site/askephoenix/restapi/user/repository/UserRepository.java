package site.askephoenix.restapi.user.repository;


import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email);

    @NonNull
    Optional<UserInfo> findById(Long id);

    @Query(value = "select u from UserInfo u where u.id = ?1")
    UserInfo searchById(Long id);
}
