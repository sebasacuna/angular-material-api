package com.angular.api.forms.services;

import com.angular.api.forms.models.entities.Elements;
import com.angular.api.forms.wsrest.CollectRequest;

import java.util.List;


public interface ElementService {

    public List<Elements> listElement();
    public boolean createElement(CollectRequest request);
    public boolean updateElement(CollectRequest request);
    public boolean deleteElement(Long id);

}
