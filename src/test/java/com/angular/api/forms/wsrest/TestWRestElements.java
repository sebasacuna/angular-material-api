package com.angular.api.forms.wsrest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.angular.api.forms.models.entities.Elements;
import com.angular.api.forms.services.ElementService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestWRestElements {

  @Mock
  ElementService elementService;

  @InjectMocks
  WRestElements wRestElements;

  @Test
  public void testWRestGetElements() {

    Elements obj = new Elements(1L, "s", 22.2, "s");
    List<Elements> list = new ArrayList<>();
    list.add(obj);

    ResponseEntity<List<Elements>> responseEntityMock = new ResponseEntity(list,
        HttpStatus.OK);

    Mockito.when(elementService.listElement()).thenReturn(responseEntityMock);

    ResponseEntity<List<Elements>> responseEntity = wRestElements
        .elements();

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

  }

  @Test
  public void testWRestPostElements() {

    CollectRequest request = new CollectRequest(1L, "name", 2D, "S");

    ResponseEntity<Boolean> responseEntityMock = new ResponseEntity(new Boolean(true),
        HttpStatus.OK);

    Mockito.when(elementService.createElement(request)).thenReturn(responseEntityMock);

    ResponseEntity<Boolean> responseEntity = wRestElements.createElement(request);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

  }

  @Test
  public void testWRestPatchElements() {

    CollectRequest request = new CollectRequest(1L, "name", 2D, "S");

    ResponseEntity<Boolean> responseEntityMock = new ResponseEntity(new Boolean(true),
        HttpStatus.OK);

    Mockito.when(elementService.updateElement(request)).thenReturn(responseEntityMock);

    ResponseEntity<Boolean> responseEntity = wRestElements.updateElement(request);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

  }

  @Test
  public void testWRestDeleteElements() {

    Long id = 1L;

    ResponseEntity<Boolean> responseEntityMock = new ResponseEntity(new Boolean(true),
        HttpStatus.OK);

    Mockito.when(elementService.deleteElement(id)).thenReturn(responseEntityMock);

    ResponseEntity<Boolean> responseEntity = wRestElements.deleteElement(id);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

  }


}
