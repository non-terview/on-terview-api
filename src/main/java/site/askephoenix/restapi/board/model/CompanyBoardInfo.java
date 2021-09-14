package site.askephoenix.restapi.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.CreationTimestamp;
import site.askephoenix.restapi.user.model.CompanyInfo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString(exclude = "companyInfo")
@Table(name = "company_board")
public class CompanyBoardInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "company_name")
    private String companyName;

    //회사 카테고리
    @Column(name = "type")
    private String type;

    @Column(name = "writer")
    private String writer;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "is_deleted")
    private boolean isDeleted;


    @Column(name = "create_date")
    @CreationTimestamp
    private Date createDate;

    @Column(name = "update_date")
    @CreationTimestamp
    private Date updateDate;

    @ManyToOne
    private CompanyInfo companyInfo;

}
