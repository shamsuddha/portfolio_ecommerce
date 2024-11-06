package com.example.ecommerce_api.controller;

import io.hms.api.controller.request_dto.GuestInfoLoginDto;
import io.hms.api.controller.request_dto.GuestInfoSearchDto;
import io.hms.api.entity.GuestInfo;
import io.hms.api.service.GuestInfoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guest-info")
@AllArgsConstructor
public class GuestInfoController {

    private final GuestInfoService guestInfoService;

    @PostMapping("/save")
    public ResponseEntity<GuestInfo> save(@RequestBody GuestInfo guestInfo) {
        return new ResponseEntity(guestInfoService.saveGuestInfo(guestInfo), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/update")
    public ResponseEntity<GuestInfo> update(@RequestBody GuestInfo guestInfo) {
        return new ResponseEntity(guestInfoService.updateGuestInfo(guestInfo), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody GuestInfo guestInfo) {
        return new ResponseEntity(guestInfoService.deleteGuestInfo(guestInfo), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<GuestInfo>> search(@RequestBody GuestInfoSearchDto guestInfoSearchDto) {
        return new ResponseEntity(guestInfoService.searchGuestInfo(guestInfoSearchDto), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/login")
    public ResponseEntity<Page<GuestInfo>> login(@RequestBody GuestInfoLoginDto guestInfoLoginDto) {
        return new ResponseEntity(guestInfoService.login(guestInfoLoginDto), HttpStatusCode.valueOf(200));
    }


}
