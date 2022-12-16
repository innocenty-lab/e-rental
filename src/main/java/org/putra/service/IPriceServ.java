package org.putra.service;

import org.putra.entity.Price;

import java.util.List;

public interface IPriceServ {
    Price insertPrice(Price price) throws Exception;
    List<Price> showAllPrice() throws Exception;
    Price editPrice(Price price, String priceId) throws Exception;
    void removePrice(String priceId) throws Exception;
}
