package edu.ntnu.idatt2003.gruppe50.view.pages;

import edu.ntnu.idatt2003.gruppe50.controller.MarketController;
import edu.ntnu.idatt2003.gruppe50.model.Stock;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;

public class MarketView implements Page{

  private final MarketController controller;
  private final TextField searchField;
  private final TableView<Stock> table;

  public MarketView(MarketController controller) {
    this.controller = controller;
    this.table = createStockTable();
    this.searchField = createSearchField();
  }


  @Override
  public Parent getView() {
    Label title = new Label("Market");
    VBox box = new VBox(10, title, searchField, table);
    return box;
  }

  @Override
  public String getTitle() {
    return "Market";
  }

  private TextField createSearchField() {
    TextField field = new TextField();
    field.setPromptText("Search by symbol or company...");

    field.textProperty().addListener((obs, oldVal, newVal) -> {
      table.getItems().setAll(controller.onSearch(newVal));
    });

    return field;
  }

  private TableView<Stock> createStockTable() {
    TableView<Stock> table = new TableView<>();

    TableColumn<Stock, String> symbolCol = new TableColumn<>("Symbol");
    symbolCol.setCellValueFactory(data ->
        new SimpleStringProperty(data.getValue().getSymbol())
    );

    TableColumn<Stock, String> companyCol = new TableColumn<>("Company");
    companyCol.setCellValueFactory(data ->
        new SimpleStringProperty(data.getValue().getCompany())
    );

    TableColumn<Stock, BigDecimal> priceCol = new TableColumn<>("Price");
    priceCol.setCellValueFactory(data ->
        new SimpleObjectProperty<>(data.getValue().getSalesPrice())
    );

    TableColumn<Stock, BigDecimal> changeCol = new TableColumn<>("Change");
    changeCol.setCellValueFactory(data ->
        new SimpleObjectProperty<>(data.getValue().getLatestPriceChange())
    );

    table.getColumns().addAll(symbolCol, companyCol, priceCol, changeCol);

    table.getItems().addAll(controller.getStocks());
    return table;
  }

}
