package com.angular.api.forms.services;

import com.angular.api.forms.models.entities.Elements;
import com.angular.api.forms.wsrest.CollectRequest;

import java.util.List;
import org.springframework.http.ResponseEntity;


public interface ElementService {

    public ResponseEntity<List<Elements>> listElement();
    public ResponseEntity<Boolean> createElement(CollectRequest request);
    public ResponseEntity<Boolean> updateElement(CollectRequest request);
    public ResponseEntity<Boolean> deleteElement(Long id);

}
