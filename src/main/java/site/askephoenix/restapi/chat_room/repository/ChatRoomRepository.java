package site.askephoenix.restapi.chat_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.askephoenix.restapi.chat_room.model.ChatRoomInfo;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoomInfo, Long> {
    @Query("select info from ChatRoomInfo info where info.deleted = false ")
    List<ChatRoomInfo> searchById(Long id);
}
