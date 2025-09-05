package StrategyPattern;
import DataModels.Order;

public interface DiscountStrategy {   
    double applyDiscount(Order order);
}

 