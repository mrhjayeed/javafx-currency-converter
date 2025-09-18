package com.main.converter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.HashMap;
import java.util.Map;

public class CurrencyDataManager {
    private static CurrencyDataManager instance;
    private ObservableList<ConversionEntry> conversionHistory;
    private Map<String, Double> exchangeRates;

    private CurrencyDataManager() {
        conversionHistory = FXCollections.observableArrayList();
        initializeExchangeRates();
    }

    public static CurrencyDataManager getInstance() {
        if (instance == null) {
            instance = new CurrencyDataManager();
        }
        return instance;
    }

    private void initializeExchangeRates() {
        exchangeRates = new HashMap<>();
        // USD as base currency
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("AUD", 1.35);
        exchangeRates.put("CNY", 6.45);
        exchangeRates.put ("BDT", 120.54);
    }

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        double fromRate = exchangeRates.get(fromCurrency);
        double toRate = exchangeRates.get(toCurrency);

        // Convert to USD first, then to target currency
        double usdAmount = amount / fromRate;
        return usdAmount * toRate;
    }

    public void addConversionEntry(ConversionEntry entry) {
        conversionHistory.add(entry);
    }

    public ObservableList<ConversionEntry> getConversionHistory() {
        return conversionHistory;
    }

    public String[] getSupportedCurrencies() {
        return exchangeRates.keySet().toArray(new String[0]);
    }
}
