package StrategyPattern;

import DataModels.Order;

public class OrderCalculator {
    public double calculateFinalPrice(Order order, DiscountStrategy strategy){
        return strategy.applyDiscount(order);
    }
}
