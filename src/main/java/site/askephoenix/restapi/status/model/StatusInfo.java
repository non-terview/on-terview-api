package site.askephoenix.restapi.status.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.user.model.UserInfo;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "status")
public class StatusInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userinfo")
    private UserInfo userInfo;

    @JoinColumn(name = "board")
    @ManyToOne
    private BoardInfo boardInfo;

    @Column(name = "state_value")
    private String state;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDate createDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDate updateDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;


    @Builder
    public StatusInfo(
            Long id, UserInfo userInfo,
            boolean isDeleted
    ) {
        this.id = id;
        this.userInfo = userInfo;
        this.isDeleted = isDeleted;
    }

}
