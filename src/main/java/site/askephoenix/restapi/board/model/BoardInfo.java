package site.askephoenix.restapi.board.model;

import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.CreationTimestamp;



import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "board")
@ToString
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
    private LocalDate createDate;

    @Column(name = "update_date")
    @CreationTimestamp
    private LocalDate updateDate;

    @Builder
    public BoardInfo(
            Long seq, String companyName, String type,
            String category, String writer, String title,
            String content, boolean isDeleted, LocalDate createDate,
            LocalDate updateDate
    ){
        this.seq = seq;
        this.category = category;
        this.content = content;
        this.updateDate = updateDate;
        this.companyName = companyName;
        this.writer = writer;
        this.isDeleted = isDeleted;
        this.type = type;
        this.title = title;
        this.createDate = createDate;
    }

}
