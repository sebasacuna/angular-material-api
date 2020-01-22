package com.angular.api.forms.services.impl;

import com.angular.api.forms.models.entities.Elements;
import com.angular.api.forms.repository.impl.ElementRepository;
import com.angular.api.forms.services.ElementService;
import com.angular.api.forms.wsrest.CollectRequest;
import com.angular.api.forms.wsrest.CollectRequestDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElementServiceImpl implements ElementService {

    @Autowired
    ElementRepository elementRepository;

    @Override
    public List<Elements> listElement() {
        return elementRepository.findAll();
    }

    @Override
    public boolean createElement(CollectRequest request) {
        Elements obj = new Elements();
        obj.setName(request.getName());
        obj.setNumber(request.getNumber());
        obj.setSymbol(request.getSymbol());
        obj.setWeight(request.getWeight());
        try {
            elementRepository.save(obj);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateElement(CollectRequest request) {
        Optional<Elements> objBefore = null;
        try {
            objBefore = elementRepository.findById(request.getNumber());
            objBefore.get().setWeight(request.getWeight());
            objBefore.get().setSymbol(request.getSymbol());
            objBefore.get().setName(request.getName());
            elementRepository.save(objBefore.get());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteElement(Long id) {
        try {
            elementRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
