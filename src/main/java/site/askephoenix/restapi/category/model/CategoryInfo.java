package site.askephoenix.restapi.category.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@Entity
@Table(name = "category")
public class CategoryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //보드인지 스케쥴인지 등등
    @Column(name = "type")
    private String type;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "is_deleted")
    private boolean isDeleted;



}
