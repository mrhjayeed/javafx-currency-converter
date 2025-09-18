package com.main.converter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;

public class HistoryController {
    @FXML
    private TableView<ConversionEntry> historyTable;

    @FXML
    private TableColumn<ConversionEntry, String> fromColumn;

    @FXML
    private TableColumn<ConversionEntry, String> toColumn;

    @FXML
    private TableColumn<ConversionEntry, Double> amountColumn;

    @FXML
    private TableColumn<ConversionEntry, Double> resultColumn;

    @FXML
    private Button backButton;

    private CurrencyDataManager dataManager;

    @FXML
    public void initialize() {
        dataManager = CurrencyDataManager.getInstance();


        fromColumn.setCellValueFactory(new PropertyValueFactory<>("fromCurrency"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("toCurrency"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));


        historyTable.setItems(dataManager.getConversionHistory());


    }

    @FXML
    protected void onBackButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("converter-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(root, 400, 300);
            stage.setTitle("Currency Converter");
            stage.setScene(scene);
        } catch (IOException e) {
            System.err.println("Error: Could not load main view");
        }
    }
}
