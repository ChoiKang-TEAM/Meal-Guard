package choikang.MealGuard.recipe.repository;

import choikang.MealGuard.recipe.entity.SearchKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, Long> {

    SearchKeyword findByKeyword(String keyword);

    List<SearchKeyword> findTop10ByOrderBySearchCountDesc();

}
