package com.calibre.forex.sending.service;

import com.calibre.forex.sending.model.Currency;

import java.util.List;

public interface CsvService {
    public String write(List<Currency> rates);
}
