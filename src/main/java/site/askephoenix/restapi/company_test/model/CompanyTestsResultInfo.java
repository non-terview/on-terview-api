package site.askephoenix.restapi.company_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import site.askephoenix.restapi.user.model.UserInfo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tests_result")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyTestsResultInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "list")
    @ManyToOne
    private TestsListInfo list;

    @ManyToOne
    @JoinColumn(name = "tester")
    private UserInfo tester;

    @Column(name = "sort_num")
    private int sort_num;

    @Column(name = "title")
    private String title;

    @Column(name = "answer")
    private String answer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Builder
    public CompanyTestsResultInfo(
            Long id, TestsListInfo list, UserInfo tester, int sort_num,
            String title, String answer, LocalDateTime createDate, LocalDateTime updateDate,
            boolean isDeleted
    ) {
        this.id = id;
        this.list = list;
        this.tester = tester;
        this.sort_num = sort_num;
        this.title = title;
        this.answer = answer;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.isDeleted = isDeleted;
    }
}

