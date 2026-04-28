package edu.ntnu.idatt2003.gruppe50.ui.view.pages;

import edu.ntnu.idatt2003.gruppe50.ui.controller.GameController;
import edu.ntnu.idatt2003.gruppe50.ui.controller.PortfolioQueryController;
import edu.ntnu.idatt2003.gruppe50.ui.model.PortfolioData;
import edu.ntnu.idatt2003.gruppe50.ui.view.components.LineChartView;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.util.List;

public class PortfolioView extends VBox implements Page {

  public PortfolioView(PortfolioQueryController queryController, GameController gameController) {
    PortfolioData portfolio = queryController.getPortfolio();

    // Make label for the different player values
    Label portfolioValueLabel = new Label(portfolio.portfolioValue().toString());
    Label playerCashLabel = new Label(portfolio.cash().toString());
    Label netWorthLabel = new Label(portfolio.netWorth().toString());

    // Make cards for the player information
    VBox portfolioValueCard = new VBox(new Label("Portfolio value:"), portfolioValueLabel);
    VBox cashBalanceCard = new VBox(new Label("Cash balance:"), playerCashLabel);
    VBox netWorthCard = new VBox(new Label("Net worth:"), netWorthLabel);

    // Make container for the different cards
    VBox cardContainer = new VBox(portfolioValueCard, cashBalanceCard, netWorthCard);

    // Make the container for the header section
    HBox topSection = new HBox(cardContainer);

    // Make the container for the share cards
    VBox shareCardContainer = new VBox();

    // Populate the container with share cards
//    for (ShareData share : portfolio.shares()) {
//      shareCardContainer.getChildren().add(ShareCardFactory.createShareCard(share));
//    }

    // Add temporary net worth line chart
    LineChartView netWorthChart = new LineChartView("Week", "Net Worth");
    List<BigDecimal> history = List.of(BigDecimal.ONE, BigDecimal.TWO);
    netWorthChart.display("Net Worth Chart", history);
    topSection.getChildren().add(netWorthChart.getChart());

    // Add the header and share container to the portfolio view
    this.getChildren().addAll(topSection, shareCardContainer);
  }

  @Override
  public Parent getView() {
    return this;
  }

  @Override
  public String getTitle() {
    return "Portfolio";
  }
}
