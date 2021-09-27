package site.askephoenix.restapi.status.repository;

import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.status.model.StatusInfo;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.List;
import java.util.Optional;

public interface StatusRepository extends JpaRepository<StatusInfo, Long> {
    Optional<List<StatusInfo>> findAllByUserInfo(UserInfo userInfo);

    Optional<StatusInfo> findByUserInfoAndBoardInfo(UserInfo userInfo,
                                                    BoardInfo boardInfo);
}
