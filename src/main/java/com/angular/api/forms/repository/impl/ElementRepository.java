package com.angular.api.forms.repository.impl;

import com.angular.api.forms.models.entities.Elements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends JpaRepository<Elements, Long> {

}
