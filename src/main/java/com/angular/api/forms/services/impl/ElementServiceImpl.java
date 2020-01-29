package com.angular.api.forms.services.impl;

import com.angular.api.forms.models.entities.Elements;
import com.angular.api.forms.repository.impl.ElementRepository;
import com.angular.api.forms.services.ElementService;
import com.angular.api.forms.wsrest.CollectRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
      logger.error("error list elements {e}", e);
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity<Long> countElements() {
    Long count;
    try {
      count = elementRepository.countElement();
      return new ResponseEntity(
          count, HttpStatus.OK);
    } catch (Exception e) {
      logger.error("error count elements {0}", e);
      return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity<List<Elements>> listElementPagination(Integer pageSize, Integer pageIndex) {
    Page<Elements> list;
    Pageable pagination =
        PageRequest.of(pageIndex, pageSize, Sort.by("number"));
    try {
      list = elementRepository.findAll(pagination);
      return new ResponseEntity(
          list.getContent(), HttpStatus.OK);
    } catch (Exception e) {
      logger.error("error list elements {e}", e);
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
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
          true, HttpStatus.OK);
    } catch (Exception e) {
      logger.error("error create element {}", e);
      return new ResponseEntity(
          false, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity<Boolean> updateElement(CollectRequest request) {
    Optional<Elements> objBefore;
    try {
      objBefore = elementRepository.findById(request.getNumber());
      if (objBefore.isPresent()) {
        objBefore.get().setWeight(request.getWeight());
        objBefore.get().setSymbol(request.getSymbol());
        objBefore.get().setName(request.getName());
        elementRepository.save(objBefore.get());
      }
      return new ResponseEntity(
          true, HttpStatus.OK);
    } catch (Exception e) {
      logger.error("error create element {e}", e);
      return new ResponseEntity(
          false, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity<Boolean> deleteElement(Long id) {
    try {
      elementRepository.deleteById(id);
      return new ResponseEntity(
          true, HttpStatus.OK);
    } catch (Exception e) {
      logger.error("error create element {}", e);
      return new ResponseEntity(
          false, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
