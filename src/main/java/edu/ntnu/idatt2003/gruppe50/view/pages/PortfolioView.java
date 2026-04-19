package edu.ntnu.idatt2003.gruppe50.view.pages;

import edu.ntnu.idatt2003.gruppe50.model.Player;
import edu.ntnu.idatt2003.gruppe50.model.Portfolio;
import edu.ntnu.idatt2003.gruppe50.model.Share;
import edu.ntnu.idatt2003.gruppe50.view.components.LineChartView;
import edu.ntnu.idatt2003.gruppe50.view.components.ShareCardFactory;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.util.List;

public class PortfolioView extends VBox implements Page {

  public PortfolioView(Player player) {
    Portfolio portfolio = player.getPortfolio();

    // Make label for the different player values
    Label portfolioValueLabel = new Label(portfolio.getNetWorth().toString());
    Label playerCashLabel = new Label(player.getMoney().toString());
    Label netWorthLabel = new Label(player.getNetWorth().toString());

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
    for (Share share : portfolio.getShares()) {
      shareCardContainer.getChildren().add(ShareCardFactory.createShareCard(share));
    }

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
