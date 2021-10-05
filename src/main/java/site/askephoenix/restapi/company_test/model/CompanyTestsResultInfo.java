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
@Table(name = "tests_result")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyTestsResultInfo {
    @Id
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "tests")
    @ManyToOne
    private CompanyTestsInfo tests;

    @ManyToOne
    @JoinColumn(name = "tester")
    private UserInfo tester;

    @Column(name = "sort_num")
    private int sort_num;

    @Column(name = "title")
    private String title;

    @Column(name = "answer")
    private String answer;

    @CreationTimestamp
    private LocalDate createDate;
    @UpdateTimestamp
    private LocalDate updateDate;

    @Builder
    public CompanyTestsResultInfo(
        Long id, CompanyTestsInfo tests, UserInfo tester, int sort_num,
        String title, String answer
    ){
        this.id = id;
        this.tests = tests;
        this.tester = tester;
        this.sort_num = sort_num;
        this.title = title;
        this.answer = answer;
    }
}

