package site.askephoenix.restapi.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class CompanyBoardInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private String seq;

    @Column(name = "company")
    private String company;

    @Column(name = "writer")
    private String writer;

    @Column(name = "title")
    private String title;

}
