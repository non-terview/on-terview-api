package site.askephoenix.restapi.company_test.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import site.askephoenix.restapi.user.model.UserInfo;

import javax.persistence.*;
import java.time.LocalDate;

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


    @CreationTimestamp
    private LocalDate createDate;
    @UpdateTimestamp
    private LocalDate updateDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Builder
    public CompanyTestsInfo(
            Long id, UserInfo writer, LocalDate createDate, LocalDate updateDate,
            boolean isDeleted
    ) {
        this.id = id;
        this.writer = writer;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.isDeleted = isDeleted;
    }
}
