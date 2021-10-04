package site.askephoenix.restapi.schedule.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import site.askephoenix.restapi.user.model.UserInfo;

import javax.persistence.*;
import java.time.LocalDate;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "schedule")
@ToString
public class ScheduleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Builder
    public ScheduleInfo(UserInfo userInfo, String title, String memo, String category , String content) {
        this.userInfo = userInfo;
        this.title = title;
        this.memo = memo;
        this.category = category;
        this.content = content;
    }

    @ManyToOne
    @JoinColumn(name = "user_infoFK")
    private UserInfo userInfo;

    //회사에서 적은 내용
    @Column(name = "title")
    private String title;

    //사용자가 직업넣는 메모
    @Column(name = "memo")
    private String memo;

    //회사 카테고리 (요식 , 개발 , 등등)
    @Column(name = "category")
    private String category;

    //회사일경우에는 메모 대신 content 가 들어감
    @Column(name = "content")
    private String content;

    @Column(name = "is_deleted")
    private boolean isDeleted;


    @Column(name = "start_date")
    private LocalDate startDate;


    @Column(name = "end_date")
    private LocalDate endDate;

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDate createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDate updateDate;

}
