package com.angular.api.forms.repository.impl;

import com.angular.api.forms.models.entities.Elements;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends JpaRepository<Elements, Long> {

  @Query(value ="SELECT COUNT(*) FROM element ",nativeQuery = true)
  Long countElement();

}
