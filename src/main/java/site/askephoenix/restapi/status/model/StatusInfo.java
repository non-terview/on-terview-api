package site.askephoenix.restapi.status.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.askephoenix.restapi.user.model.UserInfo;

import javax.persistence.*;

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


}
