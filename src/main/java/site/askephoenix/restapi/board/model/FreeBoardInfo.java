package site.askephoenix.restapi.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import site.askephoenix.restapi.user.model.UserInfo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString(exclude = "userInfo")

@Table(name = "free_board")

public class FreeBoardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    //자유게시판용 카테고리
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
    @UpdateTimestamp
    private Date updateDate;


    @ManyToOne(fetch = FetchType.LAZY)
    UserInfo userInfo;

}
