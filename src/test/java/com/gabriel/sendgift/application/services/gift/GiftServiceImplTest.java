package com.gabriel.sendgift.application.services.gift;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.sendgift.application.exceptions.GiftNotFoundException;
import com.gabriel.sendgift.application.services.user.UserServiceImpl;
import com.gabriel.sendgift.core.domain.gift.Gift;
import com.gabriel.sendgift.core.domain.gift.dto.GiftUpdateDto;
import com.gabriel.sendgift.core.domain.user.dto.UserResponse;
import com.gabriel.sendgift.core.repositories.GiftRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GiftServiceImplTest {

    @InjectMocks
    private GiftServiceImpl giftService;

    @Mock
    private GiftRepository giftRepository;

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    void getAllGifts_shouldReturnAllGifts() {
        // Arrange
        List<Gift> gifts = new ArrayList<>();
        gifts.add(new Gift("1", "Bolo", "Bolo de chocolate", "b1", "c2"));
        when(giftRepository.findAll()).thenReturn(gifts);

        // Act
        List<Gift> result = giftService.getAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Bolo", result.get(0).getName());
    }

    @Test
    void getById_shouldReturnGiftById() {
        // Arrange
        Gift gift = new Gift("1", "Bolo", "Bolo de chocolate", "b1", "c2");
        when(giftRepository.findById("1")).thenReturn(Optional.of(gift));

        // Act
        Gift result = giftService.getById("1");

        // Assert
        assertEquals("Bolo", result.getName());
    }

    @Test
    void getGiftById_whenIdNotExist_shouldReturnGiftNotFoundException() {
        // Arrange
        String giftId = "";
        when(giftRepository.findById(giftId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(
                GiftNotFoundException.class, () -> giftService.getById(giftId)
        );
    }

    @Test
    void registerGift_shouldCreateThanReturnGiftCreated() {
        // Arrange
        Gift gift = new Gift("1", "Bolo", "Bolo de chocolate", "b1", "c2");
        when(userServiceImpl.getById(eq("b1"), anyString())).thenReturn(new UserResponse());
        when(userServiceImpl.getById(eq("c2"), anyString())).thenReturn(new UserResponse());
        when(giftRepository.save(gift)).thenReturn(gift);

        // Act
        Gift result = giftService.registerGift(gift);

        // Assert
        assertNotNull(result);
        assertEquals("Bolo", result.getName());
    }

    @Test
    void updateGift_shouldUpdateThanReturnGiftUpdated() {
        // Arrange
        Gift gift = new Gift("1", "Bolo", "Bolo de chocolate", "b1", "c2");
        GiftUpdateDto giftUpdateDto = new GiftUpdateDto("Novo Nome", "Nova Descrição", "novoRecipientId");
        when(giftRepository.findById("1")).thenReturn(Optional.of(gift));
        when(giftRepository.save(gift)).thenReturn(gift);

        // Act
        Gift result = giftService.updateGift("1", giftUpdateDto);

        // Assert
        assertEquals("Novo Nome", result.getName());
        assertEquals("Nova Descrição", result.getDescription());
        assertEquals("novoRecipientId", result.getRecipientId());
    }

    @Test
    void deleteGift_shouldDeleteGiftById() {
        // Arrange
        Gift gift = new Gift("1", "Presente 1", "Descrição 1", "senderId", "recipientId");
        when(giftRepository.findById("1")).thenReturn(Optional.of(gift));

        // Act
        giftService.deleteGift("1");

        // Assert
        verify(giftRepository, times(1)).delete(gift);
    }
}