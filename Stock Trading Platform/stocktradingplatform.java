import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Stock {
    /**
     *
     */
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Portfolio {
    HashMap<String, Integer> stocks;
    double balance;

    Portfolio(double initialBalance) {
        this.stocks = new HashMap<>();
        this.balance = initialBalance;
    }

    void buyStock(String symbol, int quantity, double price) {
        if (balance >= price * quantity) {
            stocks.put(symbol, stocks.getOrDefault(symbol, 0) + quantity);
            balance -= price * quantity;
            System.out.println("Bought " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Insufficient balance to buy " + quantity + " shares of " + symbol);
        }
    }

    void sellStock(String symbol, int quantity, double price) {
        if (stocks.getOrDefault(symbol, 0) >= quantity) {
            stocks.put(symbol, stocks.get(symbol) - quantity);
            balance += price * quantity;
            System.out.println("Sold " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Insufficient shares to sell " + quantity + " shares of " + symbol);
        }
    }

    void displayPortfolio() {
        System.out.println("Portfolio:");
        for (String symbol : stocks.keySet()) {
            System.out.println(symbol + ": " + stocks.get(symbol) + " shares");
        }
        System.out.println("Balance: $" + balance);
    }
}

public class stocktradingplatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Stock> marketData = new ArrayList<>();
        marketData.add(new Stock("AAPL", 150.00));
        marketData.add(new Stock("MSFT", 3110.0));
        marketData.add(new Stock("GOOGL", 2800.00));
        marketData.add(new Stock("NFLX", 4010.0));
        marketData.add(new Stock("AMZN", 3400.00));

        Portfolio portfolio = new Portfolio(100000.00);

        while (true) {
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                System.out.println("------------------------");
                    System.out.println("Market Data:");
                    for (Stock stock : marketData) {
                        System.out.println(stock.symbol + ": $" + stock.price);
                        
                    }
                    System.out.println("------------------------\n");
                    break;
                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.next();
                    System.out.print("Enter quantity to buy: ");
                    int buyQuantity = scanner.nextInt();
                    for (Stock stock : marketData) {
                        if (stock.symbol.equals(buySymbol)) {
                            portfolio.buyStock(buySymbol, buyQuantity, stock.price);
                            System.out.println("------------------------\n");
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.next();
                    System.out.print("Enter quantity to sell: ");
                    int sellQuantity = scanner.nextInt();
                    for (Stock stock : marketData) {
                        if (stock.symbol.equals(sellSymbol)) {
                            portfolio.sellStock(sellSymbol, sellQuantity, stock.price);
                            System.out.println("------------------------\n");
                            break;
                        }
                    }
                    break;
                case 4:
                    portfolio.displayPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}