package com.leandrosnazareth.shop_validator.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDTO {
    private String identifier;
    // data da compra — usar LocalDate para corresponder ao producer (ex.: [2025,9,23])
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private LocalDate dateShop;
    private String status;
    private List<ShopItemDTO> items = new ArrayList<>();
}