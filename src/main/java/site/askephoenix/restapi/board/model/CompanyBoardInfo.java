package site.askephoenix.restapi.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter

public class CompanyBoardInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private String seq;

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

}
