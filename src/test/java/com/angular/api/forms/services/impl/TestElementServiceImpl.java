package com.angular.api.forms.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import com.angular.api.forms.models.entities.Elements;
import com.angular.api.forms.repository.impl.ElementRepository;
import com.angular.api.forms.wsrest.CollectRequest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestElementServiceImpl {

  @InjectMocks
  ElementServiceImpl elementService;

  @Mock
  ElementRepository elementRepository;

  @Test
  public void testListElement() {

    Elements obj = new Elements(1L, "s", 22.2, "s");

    List<Elements> list = new ArrayList<>();
    list.add(obj);

    when(elementRepository.findAll()).thenReturn(list);

    ResponseEntity<List<Elements>> responseEntity = elementService
        .listElement();

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

  }

  @Test
  public void testListElementException() {

    given(elementRepository.findAll()).willAnswer(invocation -> {
      throw new Exception("Exeption ");
    });

    ResponseEntity<List<Elements>> responseEntity = elementService
        .listElement();

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

  }

  @Test
  public void testCreateElement() {

    Elements obj = new Elements(1L, "s", 22.2, "s");
    CollectRequest collectRequest = new CollectRequest(1L, "s", 22.2, "s");

    when(elementRepository.save(any(Elements.class))).thenReturn(obj);

    ResponseEntity<Boolean> responseEntity = elementService
        .createElement(collectRequest);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

  }

  @Test
  public void testCreateElementException() {

    CollectRequest request = new CollectRequest(1L, "s", 22.2, "s");
    Elements obj = new Elements();
    obj.setName(request.getName());
    obj.setNumber(request.getNumber());
    obj.setSymbol(request.getSymbol());
    obj.setWeight(request.getWeight());

    given(elementRepository.save(any(Elements.class))).willAnswer(invocation -> {
      throw new Exception("Exception");
    });

    ResponseEntity<Boolean> responseEntity = elementService
        .createElement(request);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

  }

  @Test
  public void testUpdateElement() {

    Elements obj = new Elements(1L, "s", 22.2, "s");
    Elements objUpdate = new Elements(1L, "ss", 22.2, "s");
    CollectRequest collectRequest = new CollectRequest(1L, "ss", 22.2, "s");

    when(elementRepository.findById(collectRequest.getNumber())).thenReturn(
        java.util.Optional.of(obj));
    when(elementRepository.save(obj)).thenReturn(objUpdate);

    ResponseEntity<Boolean> responseEntity = elementService
        .updateElement(collectRequest);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

  }

  @Test
  public void testUpdateElementException() {

    Elements obj = new Elements(1L, "s", 22.2, "s");
    CollectRequest collectRequest = new CollectRequest(1L, "ss", 22.2, "s");

    when(elementRepository.findById(collectRequest.getNumber())).thenReturn(
        java.util.Optional.of(obj));

    given(elementRepository.save(any(Elements.class))).willAnswer(invocation -> {
      throw new Exception("Exception");
    });

    ResponseEntity<Boolean> responseEntity = elementService
        .updateElement(collectRequest);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

  }

  @Test
  public void testDeleteElement() {

    doAnswer((i) -> {
      return null;
    }).when(elementRepository).deleteById(anyLong());

    ResponseEntity<Boolean> responseEntity = elementService
        .deleteElement(anyLong());

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

  }

  @Test
  public void testDeleteElementException() {

    doAnswer((i) -> {
      throw new Exception("Exception");
    }).when(elementRepository).deleteById(anyLong());

    ResponseEntity<Boolean> responseEntity = elementService
        .deleteElement(anyLong());

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

  }


}
