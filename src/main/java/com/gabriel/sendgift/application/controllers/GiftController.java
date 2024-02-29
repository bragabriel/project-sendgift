package com.gabriel.sendgift.application.controllers;

import com.gabriel.sendgift.application.services.GiftService;
import com.gabriel.sendgift.core.domain.gift.Gift;
import com.gabriel.sendgift.core.domain.gift.dto.GiftDto;
import com.gabriel.sendgift.core.domain.gift.dto.GiftUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gifts")
public class GiftController {

    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping
    public ResponseEntity<List<Gift>> getAll(){
        List<Gift> gifts = giftService.getAll();
        return ResponseEntity.ok().body(gifts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gift> getById(@PathVariable("id") String id){
        Gift gift = giftService.getById(id);
        return ResponseEntity.ok().body(gift);
    }

    @PostMapping()
    public ResponseEntity<Gift> register(@RequestBody GiftDto giftDto) {
        Gift gift = Gift.mapToGift(giftDto);
        var response = giftService.registerGift(gift);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gift> update(@PathVariable("id") String id, @RequestBody GiftUpdateDto giftUpdateDto){
        Gift updatedGift = giftService.updateGift(id, giftUpdateDto);
        return ResponseEntity.ok().body(updatedGift);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Gift> delete(@PathVariable("id") String id){
        giftService.deleteGift(id);
        return ResponseEntity.noContent().build();
    }
}
