package site.askephoenix.restapi.board.model;

import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.CreationTimestamp;
import site.askephoenix.restapi.category.model.CategoryInfo;


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


    //자게 , 회사게 어떤 카테고리인지
    //타입이 회사게일경우에는 뽑는 스타일(개발자인지 , 뭔지 등)
    @JoinColumn(name = "category")
    @ManyToOne
    private CategoryInfo category;

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
            Long seq, String companyName,
            CategoryInfo category, String writer, String title,
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
        this.title = title;
        this.createDate = createDate;
    }

}
