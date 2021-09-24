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
@Table(name = "board")
public class BoardInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")

    private Long seq;

    @Column(name = "company_name")
    private String companyName;

    //자게인지 회사게인지
    @Column(name = "type")
    private String type;

    //자게 , 회사게 어떤 카테고리인지
    @Column(name = "category")
    private String category;

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
