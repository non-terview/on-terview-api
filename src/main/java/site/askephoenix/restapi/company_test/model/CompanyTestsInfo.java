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

/*
 * 모의 시험 작성 리스트
 * : 작성자, 작성일시, 수정일시
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "company_tests")
public class CompanyTestsInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "writer")
    @ManyToOne
    private UserInfo writer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Builder
    public CompanyTestsInfo(
            Long id, UserInfo writer, LocalDateTime createDate, LocalDateTime updateDate,
            boolean isDeleted
    ) {
        this.id = id;
        this.writer = writer;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.isDeleted = isDeleted;
    }
}
