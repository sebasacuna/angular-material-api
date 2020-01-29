package com.angular.api.forms.services;

import com.angular.api.forms.models.entities.Elements;
import com.angular.api.forms.wsrest.CollectRequest;
import java.util.List;
import org.springframework.http.ResponseEntity;


public interface ElementService {

  ResponseEntity<List<Elements>> listElement();

  ResponseEntity<List<Elements>> listElementPagination(Integer pageSize, Integer PageIndex);

  ResponseEntity<Long> countElements();

  ResponseEntity<Boolean> createElement(CollectRequest request);

  ResponseEntity<Boolean> updateElement(CollectRequest request);

  ResponseEntity<Boolean> deleteElement(Long id);

}
