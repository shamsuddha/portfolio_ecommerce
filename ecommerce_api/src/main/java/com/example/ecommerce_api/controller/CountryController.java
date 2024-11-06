package com.example.ecommerce_api.controller;

import com.example.ecommerce_api.controller.request_dto.CountrySearchDto;
import com.example.ecommerce_api.entity.Country;
import com.example.ecommerce_api.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/country")
@AllArgsConstructor
public class CountryController {

  private final CountryService countryService;

  @PostMapping("/save")
  public ResponseEntity<Country> save(@RequestBody Country country) {
    return new ResponseEntity(countryService.saveCountry(country), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<Country> update(@RequestBody Country country) {
    return new ResponseEntity(countryService.updateCountry(country), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody Country country) {
    return new ResponseEntity(countryService.deleteCountry(country), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<Country>> search(@RequestBody CountrySearchDto countrySearchDto) {
    return new ResponseEntity(countryService.searchCountry(countrySearchDto), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search-by-name")
  public ResponseEntity<Page<Country>> searchByName(@RequestBody CountrySearchDto countrySearchDto) {
    return new ResponseEntity(countryService.searchCountryByName(countrySearchDto), HttpStatusCode.valueOf(200));
  }
}
