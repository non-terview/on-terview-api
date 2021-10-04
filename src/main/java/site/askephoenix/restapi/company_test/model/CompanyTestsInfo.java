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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "company_tests")
public class CompanyTestsInfo {
    @Id
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "writer")
    @ManyToOne
    private UserInfo writer;


    @CreationTimestamp
    private LocalDate createDate;
    @UpdateTimestamp
    private LocalDate updateDate;

    @Builder
    public CompanyTestsInfo(
            Long id, UserInfo writer
    ) {
        this.id = id;
        this.writer = writer;
    }
}
