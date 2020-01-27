package com.angular.api.forms.wsrest;

import com.angular.api.forms.models.entities.Elements;
import com.angular.api.forms.services.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://app.com:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class WRestElements {

  @Autowired
  ElementService service;

  @GetMapping(path = "element")
  @ResponseBody
  public ResponseEntity<List<Elements>> elements() {
    return service.listElement();
  }

  @PostMapping(path = "element")
  @ResponseBody
  public ResponseEntity<Boolean> createElement(@RequestBody CollectRequest request) {
    return service.createElement(request);
  }

  @PatchMapping(path = "element")
  @ResponseBody
  public ResponseEntity<Boolean> updateElement(@RequestBody CollectRequest request) {
    return service.updateElement(request);
  }

  @DeleteMapping(path = "element")
  @ResponseBody
  public ResponseEntity<Boolean> deleteElement(@RequestParam(name = "id") Long id) {
    return service.deleteElement(id);
  }

}
