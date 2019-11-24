package com.calibre.forex.sending.service;

public interface RabbitService {

    public void receiveForexRate(String ratesJson);
}
