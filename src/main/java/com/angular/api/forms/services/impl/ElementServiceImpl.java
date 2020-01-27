package com.angular.api.forms.services.impl;

import com.angular.api.forms.models.entities.Elements;
import com.angular.api.forms.repository.impl.ElementRepository;
import com.angular.api.forms.services.ElementService;
import com.angular.api.forms.wsrest.CollectRequest;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElementServiceImpl implements ElementService {

  private static final Logger logger = LoggerFactory.getLogger(ElementServiceImpl.class);

  @Autowired
  ElementRepository elementRepository;

  @Override
  public ResponseEntity<List<Elements>> listElement() {
    List<Elements> list;
    try {
      list = elementRepository.findAll();
      return new ResponseEntity(
          list, HttpStatus.OK);
    } catch (Exception e) {
      logger.error("error list elements {}", e);
      return new ResponseEntity<>(new ArrayList<Elements>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity<Boolean> createElement(CollectRequest request) {
    Elements obj = new Elements();
    obj.setName(request.getName());
    obj.setNumber(request.getNumber());
    obj.setSymbol(request.getSymbol());
    obj.setWeight(request.getWeight());
    try {
      elementRepository.save(obj);
      return new ResponseEntity(
          new Boolean(true), HttpStatus.OK);
    } catch (Exception e) {
      logger.error("error create element {}", e);
      return new ResponseEntity(
          new Boolean(false), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity<Boolean> updateElement(CollectRequest request) {
    Optional<Elements> objBefore = null;
    try {
      objBefore = elementRepository.findById(request.getNumber());
      objBefore.get().setWeight(request.getWeight());
      objBefore.get().setSymbol(request.getSymbol());
      objBefore.get().setName(request.getName());
      elementRepository.save(objBefore.get());
      return new ResponseEntity(
          new Boolean(true), HttpStatus.OK);
    } catch (Exception e) {
      logger.error("error create element {}", e);
      return new ResponseEntity(
          new Boolean(false), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity<Boolean> deleteElement(Long id) {
    try {
      elementRepository.deleteById(id);
      return new ResponseEntity(
          new Boolean(true), HttpStatus.OK);
    } catch (Exception e) {
      logger.error("error create element {}", e);
      return new ResponseEntity(
          new Boolean(false), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
