package com.main.converter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class ConverterController {
    @FXML
    private TextField amountField;

    @FXML
    private ComboBox<String> fromCurrencyCombo;

    @FXML
    private ComboBox<String> toCurrencyCombo;

    @FXML
    private Label resultLabel;

    @FXML
    private Button convertButton;

    @FXML
    private Button viewHistoryButton;

    private CurrencyDataManager dataManager;

    @FXML
    public void initialize() {
        dataManager = CurrencyDataManager.getInstance();


        String[] currencies = dataManager.getSupportedCurrencies();
        fromCurrencyCombo.getItems().addAll(currencies);
        toCurrencyCombo.getItems().addAll(currencies);


        fromCurrencyCombo.setValue("USD");
        toCurrencyCombo.setValue("BDT");

        resultLabel.setText("Enter amount and select currencies");
    }

    @FXML
    protected void onConvertButtonClick() {
        try {

            if (amountField.getText().trim().isEmpty()) {
                resultLabel.setText("Error: Please enter an amount");
                return;
            }

            double amount = Double.parseDouble(amountField.getText().trim());
            if (amount <= 0) {
                resultLabel.setText("Error: Amount must be positive");
                return;
            }

            String fromCurrency = fromCurrencyCombo.getValue();
            String toCurrency = toCurrencyCombo.getValue();


            double result = dataManager.convertCurrency(fromCurrency, toCurrency, amount);


            resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, result, toCurrency));


            ConversionEntry entry = new ConversionEntry(fromCurrency, toCurrency, amount, result);
            dataManager.addConversionEntry(entry);

        } catch (NumberFormatException e) {
            resultLabel.setText("Error: Invalid amount format");
        }
    }

    @FXML
    protected void onViewHistoryButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("history-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) viewHistoryButton.getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Conversion History");
            stage.setScene(scene);
        } catch (IOException e) {
            resultLabel.setText("Error: Could not load history view");
        }
    }
}