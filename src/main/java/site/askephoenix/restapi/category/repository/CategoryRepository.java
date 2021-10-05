package site.askephoenix.restapi.category.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import site.askephoenix.restapi.category.model.CategoryInfo;

public interface CategoryRepository extends CrudRepository<CategoryInfo, Long> {

    //타입별 보드나 ,  스케쥴에 카테고리를 조회하기 위한 쿼리입니다
    @Query(value = "select c from  CategoryInfo c where c.type = ?1 and c.isDeleted = false")
    Page<CategoryInfo> searchByType(CategoryInfo categoryInfo, Pageable pageable);

}
