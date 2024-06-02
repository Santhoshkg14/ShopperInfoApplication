package com.niq.api.shoppers_info_service.exception;

public class ShopperAlreadyExistsException extends RuntimeException{
    public ShopperAlreadyExistsException(String message) {
        super(message);
    }
}
