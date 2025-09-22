package com.leandrosnazareth.shop_validator.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDTO {
    private String identifier;
    private LocalTime dateShop;
    private String status;
    private List<ShopItemDTO> items = new ArrayList<>();
}