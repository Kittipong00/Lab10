package StrategyPattern;

import DataModels.Order;

public class PercentageDiscount implements DiscountStrategy{
    private double percentage;
    public PercentageDiscount(double percentage){
        this.percentage = percentage;
    }
    @Override
    public double applyDiscount(Order order) {
        return order.getTotalPrice()*(1.0 - percentage/100.0);
    }
}
