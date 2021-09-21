package site.askephoenix.restapi.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import site.askephoenix.restapi.board.model.BoardInfo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "company_info")
public class CompanyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "join_date")
    @CreationTimestamp
    private Date joinDate;


}
